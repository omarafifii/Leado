/*
 * *
 *  * Created by Ahmed Elshaer on 12/9/19 12:08 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/9/19 12:08 PM
 *
 */

package com.leado.ui.signup

import android.app.Activity
import com.leado.data.sources.FirebaseAuthManager

class AuthRepository (
    private val firebaseAuthManager: FirebaseAuthManager
) {
    fun getUser() = firebaseAuthManager.getUser()

    fun sendVerificationCode(phoneNumber: String, signUpActivity: Activity) =
        firebaseAuthManager.verifyPhoneNumber(phoneNumber, signUpActivity)

    fun verifyPhoneCodeAndSignIn(code: String) = firebaseAuthManager.signInWithPhone(code)


    fun resendCode(activity: Activity) = firebaseAuthManager.resendCode(activity)
    fun signInVerified() = firebaseAuthManager.signInVerified()
}