package com.leado.ui.navBarFragments.Home.adapters

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.leado.model.Path
import com.leado.ui.navBarFragments.Home.HomeScrollFragmentDirections
import kotlinx.android.synthetic.main.home_scrolled_item.view.*

class HomeScrollViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var context = itemView.context

    fun bind(currentPath: Path) {
        currentPath.apply {
            itemView.iv_course.setImageResource(icon)
            itemView.bt_courseTitle.text=title
        }
        itemView.bt_courseTitle.setOnClickListener{

            val action =
                HomeScrollFragmentDirections.actionHomeFragmentToJourneyActivity(
                    currentPath.title
                )

            it.findNavController().navigate(action)
        }
    }

}