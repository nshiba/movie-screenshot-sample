package net.nshiba.movie_screenshot_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.exoplayer2.ui.PlayerView

class TextureViewActivity : AppCompatActivity() {

    private lateinit var player: MyPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_texture_view)

        val button = findViewById<Button>(R.id.screenshot_button)
        button.setOnClickListener {

        }
    }

    override fun onStart() {
        super.onStart()

        val playerView = findViewById<PlayerView>(R.id.texture_player_view)
        player = MyPlayer(this).apply {
            setup(playerView, MyPlayer.SAMPLE_HLS_URL)
        }
    }

    override fun onStop() {
        super.onStop()

        player.release()
    }
}
