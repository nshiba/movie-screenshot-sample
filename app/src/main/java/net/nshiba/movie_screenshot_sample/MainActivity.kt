package net.nshiba.movie_screenshot_sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.surface_view_button).setOnClickListener {
            startActivity(Intent(this, SurfaceViewActivity::class.java))
        }
        findViewById<Button>(R.id.texture_view_button).setOnClickListener {
            startActivity(Intent(this, TextureViewActivity::class.java))
        }
        findViewById<Button>(R.id.screenshot_button).setOnClickListener {

        }
    }
}
