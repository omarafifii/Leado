package com.leado.ui.main.navBarFragments.LeaderBoard.badgestab

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.leado.R
import com.leado.ui.main.navBarFragments.LeaderBoard.AchievementsViewModel
import kotlinx.android.synthetic.main.frgment_badges_page.*

class BadgesFragment : Fragment() {
     val TAG = this.javaClass.simpleName
    private lateinit var badgesAdapter: BadgesViewAdapter
    private lateinit var model: AchievementsViewModel

companion object {
    fun newInstance(page: Int, title: String?): BadgesFragment {
        val badgesFragment = BadgesFragment()
        val args = Bundle()
        args.putInt("someInt", page)
        args.putString("someTitle", title)
        badgesFragment.setArguments(args)
        return badgesFragment
    }
}




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model= activity?.run {
            ViewModelProvider(this).get(AchievementsViewModel::class.java)
        }!!
        badgesAdapter = BadgesViewAdapter()

        Log.d(TAG,"BadgesFragment //onCreate //${model.badgesTitle.size} ")

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG,"BadgesFragment //onCreateView //${model.badgesTitle.size} ")

        return inflater.inflate(R.layout.frgment_badges_page, container, false)
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG,"BadgesFragment// onResume //${model.badgesTitle.size} ")
        badgesAdapter.badgesTitle = model.badgesTitle
        badgesAdapter.notifyDataSetChanged()
        badgesAdapter.badgesDescription = model.badgesDescription

        recycler_achievements.apply {
       adapter = badgesAdapter

        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG,"BadgesFragment //onActivityCreated //${model.badgesTitle.size} ")

    }


}