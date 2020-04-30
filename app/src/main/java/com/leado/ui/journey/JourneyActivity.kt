package com.leado.ui.journey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import com.leado.R

class JourneyActivity : AppCompatActivity() {

    val args: JourneyActivityArgs by navArgs()

    val modelShared: JourneySharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journey)


        val courseTitle: String = args.coursetitle
        modelShared._liveCourseTitle.value = courseTitle

        findNavController(R.id.journey_nav_host_fragment)
    }

}
