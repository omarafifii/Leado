package com.leado.ui.quiz.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leado.R
import com.leado.model.Quiz

class QuizAdapter : RecyclerView.Adapter<QuizViewHolder>() {

    var quizList = mutableListOf<Quiz>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        return QuizViewHolder(inflater.inflate(R.layout.item_quiz, parent, false))
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val currentQuiz = quizList[position]

        holder.bind(currentQuiz)
    }

    override fun getItemCount(): Int {
     return  quizList.size
    }
}