package com.leado.ui.navBarFragments.Home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leado.R
import com.leado.model.Path

class HomeScrollAdapter():RecyclerView.Adapter<HomeScrollViewHolder>() {


    var patheList = mutableListOf<Path>()
set(value) {
    field.clear()
    field.addAll(value)
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeScrollViewHolder {
    val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.home_scrolled_item, parent, false)
return HomeScrollViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeScrollViewHolder, position: Int) {
        var currentPath = patheList[position]
        holder.bind(currentPath)
    }

    override fun getItemCount(): Int {

        return patheList.size
    }
}