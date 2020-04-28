package com.leado.ui.journey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import com.leado.R

class JourneyActivity : AppCompatActivity() {

//
    val journeyViewModel : JourneyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journey)



        findNavController(R.id.journey_nav_host_fragment)



    }





}
