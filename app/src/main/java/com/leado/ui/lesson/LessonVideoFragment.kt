package com.leado.ui.lesson

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.leado.R
import com.leado.model.Lesson
import com.leado.ui.journey.JourneySharedViewModel
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
    private val TAG = this.javaClass.simpleName

    private lateinit var lesson: Lesson
    private lateinit var modelShared: JourneySharedViewModel
    private val args: LessonVideoFragmentArgs by navArgs()

    private val tracker = YouTubePlayerTracker()  //youtube Listener
    private var toast: Toast? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        modelShared = requireActivity().run { ViewModelProvider(this).get(JourneySharedViewModel::class.java) }
        lesson = args.lesson

        toast = Toast.makeText(requireContext(), "", Toast.LENGTH_LONG)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (savedInstanceState != null) {
            modelShared.startVideoPoint = savedInstanceState.getFloat("videoStart", 0f) // use this with savedInstanceState Now it replaced with view model
        } else {
            modelShared.startVideoPoint = lesson.videoPoint // save last video point in vModel
        }
        return inflater.inflate(R.layout.fragment_lesson, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(youtube_player_view)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        courseHeader?.progress = lesson.id //update Progress
        courseHeader?.lessonTitle = lesson.title //update Header Title
        tv_lesson_desc.text = lesson.description //update course description

        modelShared.liveLessonStringID.observe(viewLifecycleOwner, Observer {})// observe for updates lesson by string Id

        courseHeader?.addGoToCongratsListener { findNavController().navigate(R.id.action_lessonFragment_to_congratsFragment) }
        courseHeader?.addGoToQuizListener { findNavController().navigate(R.id.action_lessonFragment_to_quizFragment) }
    }

    override fun onResume() {
        super.onResume()
        youtube_player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.addListener(tracker)

                youTubePlayer.loadOrCueVideo(lifecycle, lesson.link, modelShared.startVideoPoint)
            }

            override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
                when (state) {
                    PAUSED -> showToast("PAUSED")
                    PLAYING -> showToast("PLAYING")
                    ENDED -> {
                        courseHeader?.showQuizIcon()
                        showToast("ENDED")
                        courseHeader?.showCourseDoneIcon()
                        modelShared.updateNextLesson(lesson.id)
                    }
                    BUFFERING -> showToast("BUFFERING")
                    else -> showToast("Something Wrong!")
                }
            }
        })
    }

    private fun showToast(text: String) {
        toast?.setText(text);toast?.show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putFloat("videoStart", tracker.currentSecond)
    }

    override fun onPause() {
        super.onPause()
        //{
        //TODO :: this code block could be in vModel
        modelShared.startVideoPoint = tracker.currentSecond  //pass video progress to model
        modelShared.updates["videoPoint"] = tracker.currentSecond  //pass video progress to model
        modelShared.courseTitle = lesson.courseCategory // pass lesson category to model
        //}
        modelShared._liveUpdateLessonStringID.value = lesson.stringId // update lesson itself
    }

    override fun onDestroyView() {
        toast?.cancel()
        youtube_player_view.release()
        youtube_player_view.removeYouTubePlayerListener(tracker)
        super.onDestroyView()
    }




}
