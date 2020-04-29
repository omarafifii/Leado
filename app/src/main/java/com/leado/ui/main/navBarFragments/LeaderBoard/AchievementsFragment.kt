package com.leado.ui.main.navBarFragments.LeaderBoard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.leado.R
import com.leado.ui.main.navBarFragments.LeaderBoard.viewpager.MyPagerAdapter
import kotlinx.android.synthetic.main.fragment_achievments.*

class AchievementsFragment : Fragment() {
    val TAG = this.javaClass.simpleName

    private lateinit var model: AchievementsViewModel

    private var adapterViewPager: MyPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model = activity?.run {
            ViewModelProvider(this).get(AchievementsViewModel::class.java)
        }!!

        Log.d(TAG, "AchievementsFragment //onCreate ")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "AchievementsFragment //onCreateView")
        return inflater.inflate(R.layout.fragment_achievments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "AchievementsFragment //onViewCreated")

        adapterViewPager = MyPagerAdapter(parentFragmentManager)

        view_pager_achievements.adapter = adapterViewPager

        tabs.setupWithViewPager(view_pager_achievements)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "AchievementsFragment //onActivityCreated")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "AchievementsFragment //onResume")

    }
}