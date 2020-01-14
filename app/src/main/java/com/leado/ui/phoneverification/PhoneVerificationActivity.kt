/*
 * *
 *  * Created by Ahmed Elshaer on 12/10/19 4:01 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/10/19 4:01 PM
 *
 */

package com.leado.ui.phoneverification

import android.content.Context
import android.content.Intent
import android.content.Intent.*
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.leado.R
import com.leado.common.base.BaseActivity
import com.leado.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_phone_verification.*
import org.koin.android.viewmodel.ext.android.getViewModel

class PhoneVerificationActivity : BaseActivity<VerificationViewModel>() {
    override fun getLayoutResource(): Int = R.layout.activity_phone_verification

    override fun getFeatureViewModel(): VerificationViewModel = getViewModel()

    override fun useView() {
        text_change_number.setOnClickListener {
            finish()
        }
        resend_text.setOnClickListener {
            viewModel.resendCode(this)
        }
        input_pin.requestFocus()
        input_pin.addTextChangedListener {
            if (it.toString().length == 6)
                viewModel.validatePhone(it.toString())
        }
        text_phone_number.text = intent.extras!![PHONE_NUMBER] as String
        viewModel.success.observe(this, Observer {
            MainActivity.start(
                this@PhoneVerificationActivity,
                FLAG_ACTIVITY_CLEAR_TOP
                        or FLAG_ACTIVITY_SINGLE_TOP
                        or FLAG_ACTIVITY_CLEAR_TASK
                        or FLAG_ACTIVITY_NEW_TASK

            )
        })
        viewModel.codeResent.observe(this, Observer {
            showMessage("Resending code")
        })
    }


    companion object {
        fun start(context: Context, phoneNumber: String) {
            val intent = Intent(context, PhoneVerificationActivity::class.java)
            intent.putExtra(PHONE_NUMBER, phoneNumber)
            context.startActivity(intent)
        }

        const val PHONE_NUMBER = "phoneNumber"

    }

    override fun getLoadingView(): View? = progress_loading


}
