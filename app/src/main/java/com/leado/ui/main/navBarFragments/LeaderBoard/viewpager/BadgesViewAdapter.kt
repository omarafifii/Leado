package com.leado.ui.main.navBarFragments.LeaderBoard.viewpager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.leado.R

class BadgesViewAdapter() : RecyclerView.Adapter<BadgesViewAdapter.Viewholder>() {

    var badgesTitle =
        listOf("Badge 1", "Badge 2", "Badge 3", "Badge 4", "Badge 5", "Badge 6", "Badge 7")
    var badgesDescription =
        listOf("Desc. 1", "Desc. 2", "Desc. 3", "Desc. 4", "Desc. 5", "Desc. 6", "Desc. 7")

    class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title: TextView = itemView.findViewById(R.id.tv_BadgeName)
        var description: TextView = itemView.findViewById(R.id.tv_BadgeDetail)
        var imageResource: ImageView = itemView.findViewById(R.id.iv_badge)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.badge_item, parent, false)
        return Viewholder(
            view
        )
    }

    override fun getItemCount(): Int {
        return badgesTitle.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.title.text = badgesTitle[position]
        holder.description.setText(badgesDescription[position])
        holder.imageResource.setImageResource(R.drawable.ic_badge_quicklearner)

//
    }
}