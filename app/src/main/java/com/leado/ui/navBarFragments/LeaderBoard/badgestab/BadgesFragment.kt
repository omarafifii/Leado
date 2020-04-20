package com.leado.ui.navBarFragments.LeaderBoard.badgestab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leado.R
import com.leado.ui.navBarFragments.LeaderBoard.viewpager.BadgesViewAdapter

class BadgesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val itemView: View = inflater.inflate(R.layout.achievement_fragment, container, false)
        viewManager = LinearLayoutManager(activity)
        viewAdapter =
            BadgesViewAdapter()
        recyclerView = itemView.findViewById(R.id.recycler_achievements)
        recyclerView.adapter = viewAdapter
        recyclerView.layoutManager = viewManager
        return itemView
    }


}