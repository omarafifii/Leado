package com.leado.ui.lesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.navArgs
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.leado.R
import kotlinx.android.synthetic.main.activity_lesson.*

class LessonActivity : YouTubeBaseActivity() , YouTubePlayer.OnInitializedListener {
    val  args : LessonActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)
        courseHeader?.courseProgress = 1
        courseHeader?.lessonNumber = 1
        youtubeplayer_view.initialize(YoutubeConfig.YOUTUBE_API_KEY,this)
    }
    override fun onResume() {
        super.onResume()
        val lesson = args.lesson
courseHeader?.courseProgress = lesson.id
        courseHeader?.lessonTitle = lesson.title
        tv_lesson_desc.text=lesson.description
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
