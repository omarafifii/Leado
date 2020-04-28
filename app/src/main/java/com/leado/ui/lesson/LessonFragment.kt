package com.leado.ui.lesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.leado.R
import com.leado.model.Lesson
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_lesson.*
import kotlinx.android.synthetic.main.fragment_lesson.courseHeader
import kotlinx.android.synthetic.main.fragment_lesson.tv_lesson_desc

/**
 * A simple [Fragment] subclass.
 */
class LessonFragment : Fragment() {

    val args: LessonActivityArgs by navArgs()
    lateinit var lesson: Lesson
    val tracker = YouTubePlayerTracker()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lesson, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //hide BottomNav in JourneyFragment starting
        requireActivity().bottom_nav.visibility = View.GONE
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        lesson = args.lesson
        courseHeader?.courseProgress = lesson.id
        courseHeader?.lessonTitle = lesson.title
        courseHeader?.addGoToCongratsListener {
            findNavController().navigate(R.id.action_lessonFragment_to_congratsFragment) }
        tv_lesson_desc.text = lesson.description
    }

    override fun onResume() {
        super.onResume()
        //hide BottomNav in JourneyFragment starting
        requireActivity().bottom_nav.visibility = View.GONE


        lifecycle.addObserver(youtube_player_view)
        youtube_player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {

            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(lesson.link, 0f)
                youTubePlayer.addListener(tracker)
            }
            override fun onStateChange(
                youTubePlayer: YouTubePlayer,
                state: PlayerConstants.PlayerState
            ) {
                when (state) {
                    PlayerConstants.PlayerState.PAUSED -> Toast.makeText(
                        context, "video PAUSED",
                        Toast.LENGTH_SHORT
                    ).show()

                    PlayerConstants.PlayerState.PLAYING -> Toast.makeText(
                        context, "video PLAYING",
                        Toast.LENGTH_SHORT
                    ).show()

                    PlayerConstants.PlayerState.ENDED -> {

                        Toast.makeText(context, "video ENDED", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        youtube_player_view?.getYouTubePlayerWhenReady(object : YouTubePlayerCallback {
            override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {

            }
        })

    }

    override fun onDestroyView() {
        youtube_player_view.release()
        youtube_player_view.removeYouTubePlayerListener(tracker)
        //Show BottomNav in MainActivity starting
        requireActivity().bottom_nav.visibility = View.VISIBLE
        super.onDestroyView()

    }
}
