package com.leado.ui.main.navBarFragments.Home.adapters

import android.view.View
import androidx.core.view.marginRight
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.leado.model.Course
import com.leado.ui.main.navBarFragments.Home.HomeScrollFragmentDirections
import kotlinx.android.synthetic.main.item_home_scrolled.view.*

class HomeScrollViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

	fun bind(currentCourse: Course, courseTitleListener: ((String) -> Unit)?) {

		with(currentCourse){
			itemView.iv_course.setImageResource(icon) //icons assigned in viewModel
			itemView.bt_courseTitle.text = title
		}
		itemView.bt_courseTitle.setOnClickListener {
			courseTitleListener?.let {
				it(currentCourse.title)
			}
		}
	}

}