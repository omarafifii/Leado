package com.leado.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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
import com.leado.BuildConfig
import com.leado.R
import com.leado.ui.signup.SignUpActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableStrictMode()
//        check for Auth and if user not logged in go back to registration
//        SignUpActivity.start(this)

//
        val navController = findNavController(R.id.nav_host_fragment)
        setupBottomNavMenu(navController)

    }
    private fun setupBottomNavMenu(navController: NavController) = bottom_nav?.setupWithNavController(navController)



    companion object {
        fun start(context: Context, flags: Int = 0) {
            val intent = Intent(context, MainActivity::class.java)
            if (flags != 0)
                intent.addFlags(flags)
            context.startActivity(intent)
        }

    }
    /**
     * DEBUG
     * **/
    private fun enableStrictMode() {
        if (BuildConfig.DEBUG
        /**using only in Debug Mode**/
        ) {
            /**Create StrictMode Policy**/
            val policy = StrictMode.ThreadPolicy.Builder()
                //.detectDiskReads().detectDiskWrites().detectNetwork()
                .detectAll()
                //.penaltyDialog().penaltyDeath()
                .penaltyLog()
                /**We can get multi penalty for one policy **/
                .build();

            /**Set StrictMode Policy **/
            StrictMode.setThreadPolicy(policy);
        }
    }
}
