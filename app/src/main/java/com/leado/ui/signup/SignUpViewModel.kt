/*
 * *
 *  * Created by Ahmed Elshaer on 12/9/19 2:46 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/19/19 10:04 AM
 *
 */

package com.leado.ui.signup

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidhuman.rxfirebase2.auth.PhoneAuthCodeSentEvent
import com.androidhuman.rxfirebase2.auth.PhoneAuthVerificationCompleteEvent
import com.leado.common.base.BaseViewModel
import com.leado.common.schedulers.BaseSchedulerProvider
import io.reactivex.Observable
import java.util.regex.Pattern

class SignUpViewModel(
    private val authRepository: AuthRepository,
    schedulerProvider: BaseSchedulerProvider
) : BaseViewModel(schedulerProvider) {

    private val _success: MutableLiveData<Boolean> = MutableLiveData()
    val success: LiveData<Boolean> = _success

    private val _singInVerified: MutableLiveData<Boolean> = MutableLiveData()
    val singInVerified: LiveData<Boolean> = _singInVerified

    private var isPhoneValid = false


    fun validatePhone(phoneNumber: String) {
        validationRulesPhone(phoneNumber)
            .filter { it }
            .doOnNext { isPhoneValid = true }
            .compose(applySchedulers())
            .doOnSubscribe { _loading.postValue(true) }
            .subscribe({
                _loading.postValue(false)
            }, {
                _loading.postValue(false)
                _error.postValue(it)
            })
            .addDisposable()

    }

    fun signInVerified() {
        authRepository.signInVerified()
            .compose(applySchedulersSingle())
            .doOnSubscribe {
                _loading.postValue(true)
            }
            .subscribe({
                _loading.postValue(false)
                _singInVerified.postValue(true)
            }, {
                _loading.postValue(false)
                _error.postValue(it)
            })
            .addDisposable()

    }

    fun sendCode(phoneNumber: String, signUpActivity: Activity) {

        val sendCode = if (isPhoneValid)
            authRepository.sendVerificationCode(phoneNumber, signUpActivity)
        else
            Observable.error(Throwable("Please check fields and fill them correctly."))

        sendCode
            .compose(applySchedulers())
            .doOnSubscribe { _loading.postValue(true) }
            .subscribe({
                _loading.postValue(false)
                if (it is PhoneAuthCodeSentEvent)
                    _success.postValue(true)
                else if (it is PhoneAuthVerificationCompleteEvent) {
                    _success.postValue(false)
                    signInVerified()
                }
            }, {
                _loading.postValue(false)
                _error.postValue(it)
            })
            .addDisposable()
0
    }

    private fun validationRulesPhone(phone: String): Observable<Boolean> {
        Pattern.compile("^(010|011|012|015)[0-9]{8}\$").apply {
            if (!matcher(phone).find())
                return Observable.error(Throwable("Wrong Phone Number."))
        }
        return Observable.just(true)
    }


}
