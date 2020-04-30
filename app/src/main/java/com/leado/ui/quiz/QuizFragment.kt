package com.leado.ui.quiz

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.leado.R
import com.leado.model.Quiz
import com.leado.ui.dialog.CallDialog
import com.leado.ui.quiz.adapter.QuizAdapter
import kotlinx.android.synthetic.main.fragment_quiz.*
import kotlin.concurrent.fixedRateTimer

/**
 * A simple [Fragment] subclass.
 */
class QuizFragment : Fragment(R.layout.fragment_quiz) {

    private val TAG = this.javaClass.simpleName

    private val answers = MutableList(4) { Quiz(answer = "Answer Answer ${it + 1}") }
    private lateinit var quizAdapter: QuizAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        quizAdapter = QuizAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        quizAdapter.quizList = answers
        rv_answers.apply {
            adapter = quizAdapter
            setHasFixedSize(true)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated")

        iv_call.setOnClickListener{

            CallDialog().show(parentFragmentManager,"aaa")

        }

    }

    override fun onResume() {
        super.onResume()

   btn_submit.setOnClickListener {

       findNavController().popBackStack(R.id.journey, true)
   }

    }
}
