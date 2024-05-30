package com.bahrlou.samplevideoplayer

import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bahrlou.samplevideoplayer.databinding.ActivityExoPlayerBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayer.*
import com.google.android.exoplayer2.MediaItem

class ExoPlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExoPlayerBinding
    private lateinit var exoPlayer: ExoPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityExoPlayerBinding.inflate(layoutInflater)

        setContentView(binding.main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setUpExoplayer()

    }

    private fun setUpExoplayer() {
        exoPlayer = Builder(this).build()
        val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl))
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
        exoPlayer.play()

        binding.playerViewMain.player = exoPlayer
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer.release()
    }
}