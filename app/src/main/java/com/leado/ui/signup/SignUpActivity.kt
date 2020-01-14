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
import com.leado.R
import com.leado.common.base.BaseActivity
import com.leado.ui.main.MainActivity
import com.leado.ui.phoneverification.PhoneVerificationActivity
import kotlinx.android.synthetic.main.activity_signup.*
import org.koin.android.viewmodel.ext.android.getViewModel

class SignUpActivity : BaseActivity<SignUpViewModel>() {
    override fun getLayoutResource(): Int = R.layout.activity_signup

    override fun getFeatureViewModel(): SignUpViewModel = getViewModel()

    override fun useView() {
        text_input_phoneNumber.requestFocus()
        text_input_phoneNumber.addTextChangedListener {
            if (it.toString().isNotEmpty())
                viewModel.validatePhone(text_input_extension.text.toString() + it.toString())
        }

        button_signup.setOnClickListener {
            if (it.toString().isNotEmpty())
                viewModel.sendCode(
                    text_input_extension.text.toString() + text_input_phoneNumber.text.toString(),
                    this@SignUpActivity
                )
        }

        viewModel.success.observe(this, Observer{
            if (it)
                PhoneVerificationActivity.start(
                    this@SignUpActivity,
                    text_input_extension.text.toString() + text_input_phoneNumber.text.toString()
                )
        })

        viewModel.singInVerified.observe(this, Observer{
            MainActivity.start(
                this@SignUpActivity,
                Intent.FLAG_ACTIVITY_CLEAR_TOP
                        or Intent.FLAG_ACTIVITY_SINGLE_TOP
                        or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        or Intent.FLAG_ACTIVITY_NEW_TASK

            )
        })


    }

    override fun getLoadingView(): View? = progress_loading



    companion object {
        fun start(context: Context) {
            val intent = Intent(context, SignUpActivity::class.java)
            context.startActivity(intent)
        }


    }

}
