package com.leado.ui.main.navBarFragments.LeaderBoard.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.leado.ui.main.navBarFragments.LeaderBoard.AchievementsFragment
import com.leado.ui.main.navBarFragments.LeaderBoard.badgestab.BadgesFragment
import com.leado.ui.main.navBarFragments.LeaderBoard.leaderboardtab.LeaderboardFragment

/*
   Extension of FragmentStatePagerAdapter which intelligently caches
   all active fragments and manages the fragment lifecycles.
   Usage involves extending from SmartFragmentStatePagerAdapter as you would any other PagerAdapter.
*/

// Extend from SmartFragmentStatePagerAdapter now instead for more dynamic ViewPager items
class MyPagerAdapter(fragmentManager: FragmentManager?) :
    SmartFragmentStatePagerAdapter(fragmentManager) {
    val TAG = this.javaClass.simpleName
    private val TAB_TITLES = arrayOf("Badges", "Leaderboard")

    // Returns the fragment to display for that page
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 ->  BadgesFragment.newInstance(0, "Badges")
            1 ->  LeaderboardFragment.newInstance(1, "Leaderboard")
            else ->
                AchievementsFragment()
        }
    }
    // Returns the page title for the top indicator
    override fun getPageTitle(position: Int): CharSequence? {
        return TAB_TITLES[position]
    }
    override fun getCount(): Int {
        // Show 2 total pages.
        return TAB_TITLES.size
    }

}
