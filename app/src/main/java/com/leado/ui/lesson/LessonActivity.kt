package com.leado.ui.lesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.leado.R
import kotlinx.android.synthetic.main.activity_lesson.*

class LessonActivity : YouTubeBaseActivity() , YouTubePlayer.OnInitializedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)

        youtubeplayer_view.initialize(YoutubeConfig.YOUTUBE_API_KEY,this)

    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        p1?.loadVideo("9YlRIvdokMg")

    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        TODO("Not yet implemented")
    }
}
