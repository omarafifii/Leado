package com.leado.ui.lesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.leado.R
import com.leado.model.Lesson
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants.PlayerState.*
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import kotlinx.android.synthetic.main.fragment_lesson.*
import kotlinx.android.synthetic.main.fragment_lesson.courseHeader
import kotlinx.android.synthetic.main.fragment_lesson.tv_lesson_desc

class LessonVideoFragment : Fragment() {

    val args: LessonActivityArgs by navArgs()
    lateinit var lesson: Lesson
    val tracker = YouTubePlayerTracker()

    private val model : LessonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lesson, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        lesson = args.lesson
        courseHeader?.progress = lesson.id
        courseHeader?.lessonTitle = lesson.title

        courseHeader?.addGoToCongratsListener {
            findNavController().navigate(R.id.action_lessonFragment_to_congratsFragment)
        }

        tv_lesson_desc.text = lesson.description
    }

    override fun onResume() {
        super.onResume()
        lifecycle.addObserver(youtube_player_view)
        youtube_player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {

            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadOrCueVideo(lifecycle,lesson.link, 0f)
                youTubePlayer.addListener(tracker)
            }
            override fun onStateChange(
                youTubePlayer: YouTubePlayer,
                state: PlayerConstants.PlayerState
            ) {
                when (state) {
                    PAUSED -> Toast.makeText(context, "Video PAUSED", Toast.LENGTH_SHORT).show()

                    PLAYING -> Toast.makeText(context, "Video PLAYING", Toast.LENGTH_SHORT).show()

                    ENDED -> { Toast.makeText(context, "Video ENDED", Toast.LENGTH_SHORT).show()
                        courseHeader?.updateIconCourseDone()
                    }
                    UNKNOWN ->Toast.makeText(context, "Video UNKNOWN", Toast.LENGTH_SHORT).show()
                    UNSTARTED -> Toast.makeText(context, "Video UNSTARTED", Toast.LENGTH_SHORT).show()
                    BUFFERING -> Toast.makeText(context, "Video BUFFERING", Toast.LENGTH_SHORT).show()
                    VIDEO_CUED ->Toast.makeText(context, " VIDEO_CUED", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
    override fun onDestroyView() {
        youtube_player_view.release()
        youtube_player_view.removeYouTubePlayerListener(tracker)
        super.onDestroyView()

    }
}
