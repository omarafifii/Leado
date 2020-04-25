package com.leado.ui.navBarFragments.Home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation

import com.leado.R
import kotlinx.android.synthetic.main.fragment_home_scrolled.*
import com.leado.ui.journey.JourneyActivity as JourneyActivity

class HomeScrollFragment : Fragment() {

    private lateinit var scrollViewModel: HomeScrollViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_scrolled, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        scrollViewModel = ViewModelProvider(this).get(HomeScrollViewModel::class.java)
        // TODO: Use the ViewModel
        startJourney.setOnClickListener {
            startActivity(Intent(context,JourneyActivity::class.java))

        }

    }

}
