package com.leado.ui.journey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import com.leado.R

class JourneyActivity : AppCompatActivity() {

    val args: JourneyActivityArgs by navArgs()

    val model: JourneyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_journey)


        val courseTitle: String = args.coursetitle
        model._liveCourseTitle.value = courseTitle

        findNavController(R.id.journey_nav_host_fragment)
    }

}
