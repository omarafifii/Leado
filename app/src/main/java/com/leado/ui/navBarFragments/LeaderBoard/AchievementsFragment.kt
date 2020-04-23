package com.leado.ui.navBarFragments.LeaderBoard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.leado.R
import com.leado.ui.navBarFragments.LeaderBoard.viewpager.SectionsPagerAdapter
import kotlinx.android.synthetic.main.achievments_fragment.*

class AchievementsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.achievments_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val sectionsPagerAdapter =
            SectionsPagerAdapter(
                requireContext(),
                requireActivity().supportFragmentManager
            )
        val viewPager: ViewPager = view_pager_achievements
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = tabs

//        tabs.setSelectedTabIndicator()
        tabs.setupWithViewPager(viewPager)
    }
}