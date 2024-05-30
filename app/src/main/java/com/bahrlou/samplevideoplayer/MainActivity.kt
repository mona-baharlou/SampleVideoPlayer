package com.bahrlou.samplevideoplayer

import android.media.MediaPlayer
import android.media.session.MediaController
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bahrlou.samplevideoplayer.databinding.ActivityMainBinding

val videoUrl = "https://dunijet.ir/video/barname%20nevis%20ha%20az%20koja%20prozhe%20migiran.mp4"
//val videoUrl = "https://www.mp4.ir/Video?Watch=229257-488400010"

class MainActivity : AppCompatActivity() {
    private lateinit var player: MediaPlayer
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.videoViewMain.setVideoPath(videoUrl)

        binding.videoViewMain.setOnPreparedListener {
            binding.videoViewMain.start()

            val mediaController = android.widget.MediaController(this)
            mediaController.setMediaPlayer(binding.videoViewMain)
            binding.videoViewMain.setMediaController(mediaController)
            mediaController.setAnchorView(binding.videoViewMain)

        }

        //binding.videoViewMain.duration

    }

    override fun onStop() {
        super.onStop()

        binding.videoViewMain.stopPlayback()
    }
}