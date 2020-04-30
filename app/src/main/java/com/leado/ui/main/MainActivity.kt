package com.leado.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.navOptions
import androidx.navigation.ui.*
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.leado.BuildConfig
import com.leado.R
import com.leado.ui.main.navBarFragments.LeaderBoard.AchievementsViewModel
import com.leado.ui.signup.SignUpActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val TAG = this.javaClass.simpleName

    private lateinit var model: AchievementsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        check for Auth and if user not logged in go back to registration
//        SignUpActivity.start(this)
        model = ViewModelProvider(this).get(AchievementsViewModel::class.java)

        val navController = findNavController(R.id.nav_host_fragment)
        setupBottomNavMenu(navController)


    }

    private fun setupBottomNavMenu(navController: NavController) =
        bottom_nav?.setupWithNavController(navController)

    override fun onResume() {
        super.onResume()

    }

    companion object {
        fun start(context: Context, flags: Int = 0) {
            val intent = Intent(context, MainActivity::class.java)
            if (flags != 0)
                intent.addFlags(flags)
            context.startActivity(intent)
        }

    }


}
