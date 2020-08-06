package dev.eastar.videoplay

import android.log.Log
import android.media.MediaPlayer
import android.os.Bundle
import android.view.SurfaceHolder
import androidx.appcompat.app.AppCompatActivity
import dev.eastar.videoplay.databinding.VideoPlay1Binding

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class VideoPlay2 : AppCompatActivity() {

    private lateinit var bb: VideoPlay1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bb = VideoPlay1Binding.inflate(layoutInflater)
        setContentView(bb.root)
        playVideo()
    }

    private fun playVideo() {
        bb.video.holder.addCallback(object : SurfaceHolder.Callback {
            lateinit var player: MediaPlayer
            override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
                Log.e("~~surfaceCreated")
                player = MediaPlayer.create(this@VideoPlay2, R.raw.sample_mp4_file).apply {
                    setDisplay(surfaceHolder)
                    setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING)
                    isLooping = true
                    start()
                }
                player.setOnInfoListener { _, _, _ ->
                    bb.video.background = null
                    false
                }
            }

            override fun surfaceChanged(surfaceHolder: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
                Log.e("~~surfaceChanged")
            }

            override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
                Log.e("~~surfaceDestroyed")
                player.release()
            }
        })
    }
}