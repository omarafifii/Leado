package com.leado.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leado.R
import com.leado.data.Lesson
import com.leado.data.VisualLesson
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpCollapsingToolbar()
        initRecyclerViews()
    }

    private fun initRecyclerViews() {
        rvLessons.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        rvLessons.itemAnimator=DefaultItemAnimator()
        val lessonsAdapter = LessonsAdapter(this)
        rvLessons.adapter=lessonsAdapter
        lessonsAdapter.swapData(getDummyLessons())

        rvVisualLessons.layoutManager = GridLayoutManager(this,2,RecyclerView.VERTICAL,false)
        rvVisualLessons.itemAnimator=DefaultItemAnimator()
        val visualLessonsAdapter = VisualLessonsAdapter(this)
        rvVisualLessons.adapter=visualLessonsAdapter
        visualLessonsAdapter.swapData(getDummyVisualLessons())
    }

    private fun getDummyVisualLessons(): MutableList<VisualLesson> {
       return mutableListOf(VisualLesson("Support Systems",R.drawable.ic_library_start,true),
           VisualLesson("Core Values",R.drawable.ic_info),
           VisualLesson("Strength-Weakness",R.drawable.ic_info),
           VisualLesson("Goals & Aspiration",R.drawable.ic_info)
           )
    }

    private fun getDummyLessons(): MutableList<Lesson> {
        return mutableListOf(Lesson("Support Systems","Lesson 1","Building an integral support system,\n" +
                "Pushes you to grow, strech more, mainly asks why things wont work, and bullet proofs ideas"),
            Lesson("Core Values","Lesson 2","Building an integral support system,\n" +
                    "Pushes you to grow, strech more, mainly asks why things wont work, and bullet proofs ideas"),
            Lesson("Strength-Weakness","Lesson 3","Building an integral support system,\n" +
                    "Pushes you to grow, strech more, mainly asks why things wont work, and bullet proofs ideas"),
            Lesson("Goals & Aspiration","Lesson 4","Building an integral support system,\n" +
                    "Pushes you to grow, strech more, mainly asks why things wont work, and bullet proofs ideas"))
    }

    private fun setUpCollapsingToolbar(){
        setSupportActionBar(toolbar)
        collapsing_toolbar.title = ""
        collapsing_toolbar.setExpandedTitleColor(resources.getColor(android.R.color.transparent))
        val primaryDark = resources.getColor(R.color.colorPrimaryDark)
        val primary = resources.getColor(R.color.colorPrimary)
        collapsing_toolbar.setContentScrimColor(primary)
        collapsing_toolbar.setStatusBarScrimColor(primaryDark)

    }
}
