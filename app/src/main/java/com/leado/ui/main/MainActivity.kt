package com.leado.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leado.R
import com.leado.ui.signup.SignUpActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //check for Auth and if user not logged in go back to registration
        SignUpActivity.start(this)
    }
    companion object {
        fun start(context: Context ,flags: Int = 0) {
            val intent = Intent(context, MainActivity::class.java)
            if (flags != 0)
                intent.addFlags(flags)
            context.startActivity(intent)
        }

    }

}
