package com.leado.ui.journey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.leado.R

class JourneyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journey)


        val navController = findNavController(R.id.journey_nav_host_fragment)
    }

}
