package net.nshiba.movie_screenshot_sample

import android.content.Context
import android.net.Uri
import android.util.Log
import android.webkit.WebSettings
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

class MyPlayer(private val context: Context) {

    private var player: Player? = null

    fun setup(playerView: PlayerView, url: String) {
        if (player != null) {
            Log.d("MyPlayer", "A player does be already setup.")
            return
        }

        val dataSourceFactory =
            DefaultDataSourceFactory(context, WebSettings.getDefaultUserAgent(context))
        val hlsMediaSource = HlsMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(url))

        player = SimpleExoPlayer.Builder(context).build().apply {
            prepare(hlsMediaSource)
            playWhenReady = true
        }

        playerView.player = player
    }

    fun release() {
        player?.release()
        player = null
    }

    companion object {

        const val SAMPLE_HLS_URL =
            "https://devstreaming-cdn.apple.com/videos/streaming/examples/img_bipbop_adv_example_ts/master.m3u8"
    }
}