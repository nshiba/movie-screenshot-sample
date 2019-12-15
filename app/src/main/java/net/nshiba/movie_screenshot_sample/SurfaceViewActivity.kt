package net.nshiba.movie_screenshot_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceView
import android.widget.Button
import com.google.android.exoplayer2.ui.PlayerView

class SurfaceViewActivity : AppCompatActivity() {

    private lateinit var player: MyPlayer

    private lateinit var playerView: PlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surface_view)

        val button = findViewById<Button>(R.id.screenshot_button)
        playerView = findViewById(R.id.surface_player_view)

        button.setOnClickListener {
            ScreenshotHelper.takeScreenshotForSurfaceView(
                this,
                playerView.videoSurfaceView as SurfaceView
            )
        }
    }

    override fun onStart() {
        super.onStart()
        player = MyPlayer(this).apply {
            setup(playerView, MyPlayer.SAMPLE_HLS_URL)
        }
    }

    override fun onStop() {
        super.onStop()

        player.release()
    }
}
