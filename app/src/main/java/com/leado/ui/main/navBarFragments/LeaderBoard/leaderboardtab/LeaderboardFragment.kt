package com.leado.ui.main.navBarFragments.LeaderBoard.leaderboardtab

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.leado.R
import com.leado.ui.main.navBarFragments.LeaderBoard.AchievementsViewModel
import kotlinx.android.synthetic.main.badges_page.*

class LeaderboardFragment : Fragment() {
    val TAG = this.javaClass.simpleName
    private lateinit var leaderAdapter: LeaderboardViewAdapter
    private lateinit var model: AchievementsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"LeaderboardFragment //onCreate")
        model = activity?.run {
            ViewModelProvider(this).get(AchievementsViewModel::class.java)
        }!!
        leaderAdapter = LeaderboardViewAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG,"LeaderboardFragment //onCreateView")
        return inflater.inflate(R.layout.badges_page, container, false)
    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        Log.d(TAG,"LeaderboardFragment //onViewCreated")
//
//        recycler_achievements.apply {
//            adapter = leaderAdapter
//            layoutManager = layoutManager
//        }
//    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "BadgesFragment// onResume before assign//${model.badgeByList.size} ")
//        model.updateUsers()
        leaderAdapter.userList = model.userByList
        leaderAdapter.notifyDataSetChanged()
        Log.d(TAG, "BadgesFragment// onResume after assign //${model.badgeByList.size} ")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        model.liveUserByList.observe(viewLifecycleOwner, Observer {
            model.userByList = it
        })
        recycler_achievements.apply {
            adapter = leaderAdapter
        }
        Log.d(TAG, "BadgesFragment //onActivityCreated //${model.badgeByList.size} ")
    }

    companion object{
        fun newInstance(page: Int, title: String?):LeaderboardFragment{
            val leaderboardFragment = LeaderboardFragment()
            val args = Bundle()
            args.putInt("someInt", page)
            args.putString("someTitle", title)
            leaderboardFragment.setArguments(args)
            return leaderboardFragment
        }
    }
}