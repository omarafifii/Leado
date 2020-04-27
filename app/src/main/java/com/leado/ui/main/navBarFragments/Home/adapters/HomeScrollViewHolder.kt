package com.leado.ui.main.navBarFragments.Home.adapters

import android.view.View
import androidx.core.view.marginRight
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.leado.R
import com.leado.model.Course
import com.leado.model.Path
import com.leado.ui.main.navBarFragments.Home.HomeScrollFragmentDirections
import kotlinx.android.synthetic.main.home_scrolled_item.view.*

class HomeScrollViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(currentCourse: Course) {

        currentCourse.apply {
            itemView.iv_course.setImageResource(icon) //icons assigned in viewModel
            itemView.bt_courseTitle.text = title
        }
        itemView.bt_courseTitle.setOnClickListener {
            val action =
                HomeScrollFragmentDirections.actionHomeFragmentToJourney(currentCourse.title)
            it.findNavController().navigate(action)
        }
    }
}