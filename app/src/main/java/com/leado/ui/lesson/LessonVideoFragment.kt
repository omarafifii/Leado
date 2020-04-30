package com.leado.ui.lesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
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
import kotlinx.android.synthetic.main.course_header_layout.view.*
import kotlinx.android.synthetic.main.fragment_lesson.*
import kotlinx.android.synthetic.main.fragment_lesson.courseHeader
import kotlinx.android.synthetic.main.fragment_lesson.tv_lesson_desc

class LessonVideoFragment : Fragment() {
    private lateinit var lesson: Lesson
    val model: LessonViewModel by viewModels()

    private val tracker = YouTubePlayerTracker()

    private val args: LessonActivityArgs by navArgs()
    private var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toast = Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (savedInstanceState != null) {
            model.startVideoSeconds = savedInstanceState.getFloat("videoStart", 0f)
        }
        return inflater.inflate(R.layout.fragment_lesson, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lesson = args.lesson
        lifecycle.addObserver(youtube_player_view)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        courseHeader?.progress = lesson.id
        courseHeader?.lessonTitle = lesson.title
        tv_lesson_desc.text = lesson.description

        courseHeader?.addGoToCongratsListener {
            findNavController().navigate(R.id.action_lessonFragment_to_congratsFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        youtube_player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.addListener(tracker)
                youTubePlayer.loadOrCueVideo(lifecycle, lesson.link, model.startVideoSeconds)
            }
            override fun onStateChange(
                youTubePlayer: YouTubePlayer,
                state: PlayerConstants.PlayerState
            ) {
                when (state) {
                    PAUSED -> showToast("PAUSED")
                    PLAYING -> showToast("PLAYING")
                    ENDED -> {
                        showToast("ENDED")
                        courseHeader?.updateIconCourseDone()
                    }
                    BUFFERING -> showToast("BUFFERING")
                    else -> showToast("Something Wrong!")
                }
            }
        })
    }

    private fun showToast(text: String) {
        toast?.setText(text)
        toast?.show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putFloat("videoStart", tracker.currentSecond)
    }

    override fun onDestroyView() {
        toast?.cancel()
//        model.startVideoSeconds = tracker.currentSecond
        youtube_player_view.release()
        youtube_player_view.removeYouTubePlayerListener(tracker)
        super.onDestroyView()

    }
}
