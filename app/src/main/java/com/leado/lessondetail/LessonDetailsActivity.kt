package com.leado.lessondetail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.leado.R
import com.leado.data.Lesson
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import kotlinx.android.synthetic.main.activity_lesson_details.*
import kotlinx.android.synthetic.main.lesson_visual_item.*


class LessonDetailsActivity : AppCompatActivity() {
    val libraryList = mutableListOf(R.drawable.ic_library_start,R.drawable.book_shelf1, R.drawable.book_shelf2, R.drawable.book_shelf3)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_details)
        initViewPager()
        vpLessons.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                ivLesson.setImageDrawable(getDrawable(libraryList[position]))

            }

        })
        youtube_player_view.addYouTubePlayerListener(object : YouTubePlayerListener {
            override fun onApiChange(youTubePlayer: YouTubePlayer) {

            }

            override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {

            }

            override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {

            }

            override fun onPlaybackQualityChange(
                youTubePlayer: YouTubePlayer,
                playbackQuality: PlayerConstants.PlaybackQuality
            ) {

            }

            override fun onPlaybackRateChange(
                youTubePlayer: YouTubePlayer,
                playbackRate: PlayerConstants.PlaybackRate
            ) {

            }

            override fun onReady(youTubePlayer: YouTubePlayer) {

            }

            override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
                if (state == PlayerConstants.PlayerState.ENDED) {
                    youTubePlayer.loadVideo("07d2dXHYb94",0f)
                    youTubePlayer.play()
                    if (vpLessons.currentItem < 3)
                        vpLessons.currentItem = vpLessons.currentItem + 1
                    else
                        startActivity(Intent(this@LessonDetailsActivity,CongratsActivity::class.java))
                }

            }

            override fun onVideoDuration(youTubePlayer: YouTubePlayer, duration: Float) {

            }

            override fun onVideoId(youTubePlayer: YouTubePlayer, videoId: String) {

            }

            override fun onVideoLoadedFraction(youTubePlayer: YouTubePlayer, loadedFraction: Float) {

            }

        })

    }

    private fun initViewPager() {
        val introduction = getString(R.string.Introduction)
        val adapterViewPager = LessonDetailsPagerAdapter(
            supportFragmentManager,
            mutableListOf(
                Lesson("Lesson 1", introduction, getString(R.string.lesson_text)),
                Lesson("Lesson 2", introduction, getString(R.string.lesson_text)),
                Lesson("Lesson 3", introduction, getString(R.string.lesson_text)),
                Lesson("Lesson 3", introduction, getString(R.string.lesson_text))
            )
        )
        vpLessons.adapter = adapterViewPager
        stepper.setViewPager(vpLessons)

    }

    class LessonDetailsPagerAdapter(fragmentManager: FragmentManager, val lessonsList: MutableList<Lesson>) :
        FragmentPagerAdapter(fragmentManager) {

        // Returns total number of pages
        override fun getCount(): Int {
            return lessonsList.size
        }

        // Returns the fragment to display for that page
        override fun getItem(position: Int): Fragment {
            return CourseDetailFragment.newInstance(lessonsList[position])
        }


    }
}
