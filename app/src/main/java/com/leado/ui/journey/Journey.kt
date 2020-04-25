package com.leado.ui.journey

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import com.leado.R
import kotlinx.android.synthetic.main.activity_journey.*

/**
 * A simple [Fragment] subclass.
 */
class Journey : Fragment(R.layout.fragment_journey) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var courseProgress =3

        requireActivity().courseHeader.courseProgress = courseProgress

        requireActivity().button.setOnClickListener {
            requireActivity().courseHeader.courseProgress = ++courseProgress
            requireActivity().courseHeader.lessonNumber= courseProgress
        }

        requireActivity().courseHeader.addListener {
            Toast.makeText(context, "Congratulations $it", Toast.LENGTH_LONG).show()


        }
    }
}
