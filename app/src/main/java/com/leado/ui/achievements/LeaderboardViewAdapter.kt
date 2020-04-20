package com.leado.ui.achievements

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.leado.R

class LeaderboardViewAdapter() : RecyclerView.Adapter<LeaderboardViewAdapter.Viewholder>() {

    var memberName = listOf("Name 1", "Name 2", "Name 3", "Name 4", "Name 5", "Name 6", "Name 7")
    var points = listOf(
        "Points. 1",
        "Points. 2",
        "Points. 3",
        "Points. 4",
        "Points. 5",
        "Points. 6",
        "Points. 7"
    )


    class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var memberNameTV: TextView = itemView.findViewById(R.id.tv_Leaderboard_Name)
        var pointsTV: TextView = itemView.findViewById(R.id.tv_Leaderboard_points)
        var imageResource: ImageView = itemView.findViewById(R.id.iv_leaderboard_profile)
        var rank: EqualWidthHeightTextView = itemView.findViewById(R.id.tv_rank)
//        var circularRank: CircularTextView = itemView.findViewById(R.id.circular_textview_rank)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.leaderboard_item, parent, false)
        return Viewholder(view)
    }

    override fun getItemCount(): Int {
        return memberName.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.imageResource.setImageResource(R.drawable.quick_learner)
        holder.memberNameTV.text = memberName[position]
        holder.pointsTV.text = points[position]
        holder.rank.text = (position + 1).toString()
        if (position > 2) {
            holder.rank.setTextColor(Color.parseColor("#d5d8dd"))
//            holder.rank.setBackground(R.drawable.textview_circle_grey)
            holder.rank.setBackgroundResource(R.drawable.textview_circle_grey)
//            holder.rank.setTextColor(R.color.colorLeaderboardGrey)
//            holder.rank.textColors = #d5d8dd
        }
//        holder.circularRank.text = (position + 1).toString()
//        holder.circularRank.setStrokeColor("#22d0c4")
//        holder.circularRank.setSolidColor("#d5fdfb")
//        holder.circularRank.setStrokeWidth(1)

//        Log.e("Circle", holder.circularRank.contentDescription.toString())

    }


}