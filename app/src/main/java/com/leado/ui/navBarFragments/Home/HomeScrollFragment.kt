package com.leado.ui.navBarFragments.Home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.firebase.firestore.FirebaseFirestore

import com.leado.R
import kotlinx.android.synthetic.main.fragment_home_scrolled.*
import com.leado.ui.journey.JourneyActivity as JourneyActivity

class HomeScrollFragment : Fragment() {

    private lateinit var scrollViewModel: HomeScrollViewModel
    val db = FirebaseFirestore.getInstance()

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



        val user = hashMapOf(
            "first" to "Ada",
            "last" to "Lovelace",
            "born" to 1815
        )
// Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.e("Firestore", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error adding document", e)
            }


        startJourney.setOnClickListener {
            startActivity(Intent(context,JourneyActivity::class.java))

        }

        db.collection("users").document("user")
        .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d("Firestore", "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d("Firestore", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Firestore", "get failed with ", exception)
            }



    }

}
