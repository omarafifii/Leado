package com.leado.ui.main.navBarFragments.LeaderBoard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.leado.R
import com.leado.ui.main.navBarFragments.LeaderBoard.viewpager.SectionsPagerAdapter
import kotlinx.android.synthetic.main.fragment_achievments.*

class AchievementsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_achievments, container, false)
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