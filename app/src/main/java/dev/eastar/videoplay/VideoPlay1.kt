package dev.eastar.videoplay

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.eastar.videoplay.databinding.VideoPlay1Binding

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class VideoPlay1 : AppCompatActivity() {

    private lateinit var bb: VideoPlay1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bb = VideoPlay1Binding.inflate(layoutInflater)
        setContentView(bb.root)
        playVideo()
    }

    private fun playVideo() {
        bb.video.apply {
            setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.sample_mp4_file))
            setOnPreparedListener {
                it.isLooping = true
            }
            setOnInfoListener { _, _, _ ->
                bb.video.background = null
                false
            }
            start()
        }
    }
}