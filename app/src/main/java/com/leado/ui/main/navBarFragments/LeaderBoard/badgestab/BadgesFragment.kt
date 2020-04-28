package com.leado.ui.main.navBarFragments.LeaderBoard.badgestab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leado.R
import kotlinx.android.synthetic.main.badges_page.*
import com.leado.ui.main.navBarFragments.LeaderBoard.badgestab.BadgesViewmodel as BadgesViewmodel1

class BadgesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: BadgesViewAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var model: BadgesViewmodel1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val itemView: View = inflater.inflate(R.layout.badges_page, container, false)
        model = ViewModelProvider(this).get(BadgesViewmodel1::class.java)
        viewManager = LinearLayoutManager(activity)
        viewAdapter = BadgesViewAdapter()
        viewAdapter.badgesTitle = model.badgesTitle
        viewAdapter.badgesDescription = model.badgesDescription
//        recyclerView = itemView.findViewById(R.id.recycler_achievements)
//        recyclerView.adapter = viewAdapter
//        recyclerView.layoutManager = viewManager
        return itemView
    }

    override fun onResume() {
        super.onResume()
        viewAdapter.badgesTitle = model.badgesTitle
        viewAdapter.badgesDescription = model.badgesDescription
        recycler_achievements.apply {
            this.adapter = viewAdapter
            this.layoutManager = viewManager
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        model.badgesTitle.observe(viewLifecycleOwner, Observer { courseList->
//
//            homeAdapter.courseList = courseList
//
//        })

        model.mutableBadgesTitle.observe(viewLifecycleOwner, Observer {
            viewAdapter.badgesTitle = it
        })
        model.mutableBadgesDescription.observe(viewLifecycleOwner, Observer {
            viewAdapter.badgesDescription = it
        })


        recycler_achievements.apply {
            this.adapter = viewAdapter
            this.layoutManager = viewManager
        }
//        viewManager = LinearLayoutManager(activity)
//        viewAdapter = BadgesViewAdapter()
//        recyclerView = itemView.findViewById(R.id.recycler_achievements)
//        recyclerView = requireView().findViewById(R.id.recycler_achievements)
//        recyclerView.adapter = viewAdapter
//        recyclerView.layoutManager = viewManager
    }


}