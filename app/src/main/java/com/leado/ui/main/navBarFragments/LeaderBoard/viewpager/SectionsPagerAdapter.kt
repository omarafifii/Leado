package com.leado.ui.main.navBarFragments.LeaderBoard.viewpager

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.leado.ui.main.navBarFragments.LeaderBoard.badgestab.BadgesFragment
import com.leado.ui.main.navBarFragments.LeaderBoard.leaderboardtab.LeaderboardFragment

class SectionsPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    val TAG = this.javaClass.simpleName
    private val TAB_TITLES = arrayOf("Badges", "Leaderboard")

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return if (position == 0) {
            val badges = BadgesFragment.newInstance(0, "Badges")
            Log.d(TAG, "SectionsPagerAdapter //## $badges")
            badges
        }else
        {
            val leader = LeaderboardFragment.newInstance(1, "Leaderboard")
            Log.d(TAG,"SectionsPagerAdapter //## $leader")
            leader
        }
    }
    override fun getPageTitle(position: Int): CharSequence? {
        return TAB_TITLES[position]
    }
    override fun getCount(): Int {
        // Show 2 total pages.
        return TAB_TITLES.size
    }
}