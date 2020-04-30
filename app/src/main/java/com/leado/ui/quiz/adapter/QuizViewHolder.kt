package com.leado.ui.quiz.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.leado.model.Quiz
import kotlinx.android.synthetic.main.item_quiz.view.*

class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bind(quiz: Quiz) {

        itemView.apply {
            tv_answer.text = quiz.answer

            tv_answerNum.text = "Answer${adapterPosition+1}"
        }

    }

}