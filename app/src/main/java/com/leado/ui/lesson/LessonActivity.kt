package com.leado.ui.lesson

import android.os.Bundle
import android.widget.Toast

import androidx.navigation.navArgs
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.leado.R
import com.leado.model.Lesson
import kotlinx.android.synthetic.main.activity_lesson.*

class LessonActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

//    val args: LessonActivityArgs by navArgs()
    lateinit var lesson: Lesson


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)


        youtubeplayer_view.initialize(YoutubeConfig.YOUTUBE_API_KEY, this)
    }

    override fun onResume() {
        super.onResume()

//        lesson = args.lesson
//        courseHeader?.progress = lesson.id
//        courseHeader?.lessonTitle = lesson.title

        courseHeader?.addGoToCongratsListener {
        }

        tv_lesson_desc.text = lesson.description
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        p1?.loadVideo(lesson.link)
        p1?.setPlayerStateChangeListener(playerStateChangeListener)

    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {

    }

    private val playerStateChangeListener = object : YouTubePlayer.PlayerStateChangeListener {
        override fun onAdStarted() {
            Toast.makeText(
                baseContext,
                "Click Ad now, make the video creator rich!",
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun onLoading() {
        }

        override fun onVideoStarted() {
            Toast.makeText(baseContext, "Video has started", Toast.LENGTH_SHORT).show()
        }

        override fun onLoaded(p0: String?) {
        }

        override fun onVideoEnded() {


            Toast.makeText(
                baseContext,
                "Congratulations! You've completed another video.",
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {
        }
    }

}

