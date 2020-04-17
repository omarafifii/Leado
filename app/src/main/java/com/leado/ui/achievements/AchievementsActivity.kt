package com.leado.ui.achievements

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.leado.R

class AchievementsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("achievements2", "before super.onCreate")
        super.onCreate(savedInstanceState)
        Log.e("achievements2", "before set content view")
        setContentView(R.layout.achievments_page)
        Log.e("achievements2", "after set content view")
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager_achievements)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)

//        tabs.setSelectedTabIndicator()
        tabs.setupWithViewPager(viewPager)
    }


}