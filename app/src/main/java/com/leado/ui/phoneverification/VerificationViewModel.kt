/*
 * *
 *  * Created by Ahmed Elshaer on 12/10/19 4:34 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/10/19 2:29 PM
 *
 */

package com.leado.ui.phoneverification

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leado.common.base.BaseViewModel
import com.leado.common.schedulers.BaseSchedulerProvider
import com.leado.ui.signup.AuthRepository

class VerificationViewModel (
    private val authRepository: AuthRepository,
    schedulerProvider: BaseSchedulerProvider
) : BaseViewModel(schedulerProvider) {

    private val _success: MutableLiveData<Boolean> = MutableLiveData()
    val success: LiveData<Boolean> = _success

    private val _codeResent: MutableLiveData<Boolean> = MutableLiveData()
    val codeResent: LiveData<Boolean> = _codeResent


    fun validatePhone(code: String) {
        authRepository.verifyPhoneCodeAndSignIn(code)
            .compose(applySchedulersSingle())
            .doOnSubscribe { _loading.postValue(true) }
            .subscribe({
                _loading.postValue(false)
                _success.postValue(true)
            }, {
                _loading.postValue(false)
                _error.postValue(it)
            })
            .addDisposable()

    }

    fun resendCode(activity: Activity) {
        authRepository.resendCode(activity)
            .compose(applySchedulers())
            .subscribe({
                _loading.postValue(false)
                _codeResent.postValue(true)

            }, {
                _loading.postValue(false)
                _error.postValue(it)
            })
            .addDisposable()
    }




}
