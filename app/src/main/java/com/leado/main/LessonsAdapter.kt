package com.leado.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leado.R
import com.leado.data.Lesson
import com.leado.lessondetail.LessonDetailsActivity
import kotlinx.android.synthetic.main.lesson_card_item.view.*
import java.util.*

class LessonsAdapter(val content:Context) : RecyclerView.Adapter<LessonsAdapter.LessonsViewHolder>() {

    private var data: MutableList<Lesson> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonsViewHolder {
        return LessonsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.lesson_card_item, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: LessonsViewHolder, position: Int) = holder.bind(data[position])

    fun swapData(data: MutableList<Lesson>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun addItem(item: Lesson) {
        this.data.add(item)
        notifyItemInserted(data.size - 1)

    }

    fun removeItem(position: Int) {
        this.data.removeAt(position)
        notifyItemRemoved(position)
    }

    fun updateItem(item: Lesson, position: Int) {
        this.data[position] = item
        notifyItemChanged(position)
    }

    class LessonsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Lesson) = with(itemView) {

            itemView.apply {
                tvTitle.text = item.title
                tvId.text = item.id
                tvDescription.text = item.description
                bStart.setOnClickListener {
                    context.startActivity(Intent(context,LessonDetailsActivity::class.java))
                }
            }
            setOnClickListener {
                context.startActivity(Intent(context,LessonDetailsActivity::class.java))

            }
        }

    }
}