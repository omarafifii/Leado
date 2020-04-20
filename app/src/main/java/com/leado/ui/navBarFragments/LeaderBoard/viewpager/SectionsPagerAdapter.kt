package com.leado.ui.navBarFragments.LeaderBoard.viewpager

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.leado.ui.navBarFragments.LeaderBoard.badgestab.BadgesFragment
import com.leado.ui.navBarFragments.LeaderBoard.leaderboardtab.LeaderboardFragment

private val TAB_TITLES = arrayOf(
    "Badges",
    "Leaderboard"
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return if (position == 0)
            BadgesFragment()
        else
            LeaderboardFragment()

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TAB_TITLES[position]
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}