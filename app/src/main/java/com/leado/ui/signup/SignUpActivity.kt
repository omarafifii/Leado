/*
 * *
 *  * Created by Ahmed Elshaer on 12/8/19 12:56 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/8/19 12:56 PM
 *
 */

package com.leado.ui.signup

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.google.firebase.firestore.FirebaseFirestore
import com.leado.R
import com.leado.common.base.BaseActivity
import com.leado.model.*
import com.leado.ui.main.MainActivity
import com.leado.ui.phoneverification.PhoneVerificationActivity
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.fragment_onboarding.*
import org.koin.android.viewmodel.ext.android.getViewModel

class SignUpActivity : BaseActivity<SignUpViewModel>() {
//    override fun getLayoutResource(): Int = R.layout.activity_signup
    override fun getLayoutResource(): Int = R.layout.fragment_onboarding

    override fun getFeatureViewModel(): SignUpViewModel = getViewModel()
val db = FirebaseFirestore.getInstance()

    override fun useView() {

        bt_GetStarted.setOnClickListener {


            MainActivity.start(
                /**
                 * for testing
                 **/
                this@SignUpActivity,
                Intent.FLAG_ACTIVITY_CLEAR_TOP
                        or Intent.FLAG_ACTIVITY_SINGLE_TOP
                        or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        or Intent.FLAG_ACTIVITY_NEW_TASK
            )
        }

        //        text_input_phoneNumber.requestFocus()
//        text_input_phoneNumber.addTextChangedListener {
//            if (it.toString().isNotEmpty())
//                viewModel.validatePhone(it.toString())
//        }
//        button_signup.setOnClickListener {
//
//            MainActivity.start(
//                /**
//                 * for testing
//                 *
//                 **/
//                this@SignUpActivity,
//                Intent.FLAG_ACTIVITY_CLEAR_TOP
//                        or Intent.FLAG_ACTIVITY_SINGLE_TOP
//                        or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                        or Intent.FLAG_ACTIVITY_NEW_TASK
//            )
//
//            if (it.toString().isNotEmpty())
//                viewModel.sendCode(
//                    text_input_extension.text.toString() + text_input_phoneNumber.text.toString(),
//                    this@SignUpActivity
//                )
//        }
//
//        viewModel.success.observe(this, Observer{
//            if (it)
//                PhoneVerificationActivity.start(
//                    this@SignUpActivity,
//                    text_input_extension.text.toString() + text_input_phoneNumber.text.toString()
//                )
//        })
//
//        viewModel.singInVerified.observe(this, Observer{
//            MainActivity.start(
//                this@SignUpActivity,
//                Intent.FLAG_ACTIVITY_CLEAR_TOP
//                        or Intent.FLAG_ACTIVITY_SINGLE_TOP
//                        or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                        or Intent.FLAG_ACTIVITY_NEW_TASK
//
//            )
//        })


    }

    override fun getLoadingView(): View? = progress_loading
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, SignUpActivity::class.java)
            context.startActivity(intent)
        }


    }
}
