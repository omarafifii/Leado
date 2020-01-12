/*
 * *
 *  * Created by Ahmed Elshaer on 12/9/19 10:15 AM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/9/19 10:15 AM
 *
 */

package com.leado.data.sources

import android.app.Activity
import android.util.Log
import com.androidhuman.rxfirebase2.auth.*
import com.google.firebase.auth.*
import io.reactivex.Observable
import java.util.*
import java.util.concurrent.TimeUnit

class FirebaseAuthManager {
    private val authInstance = FirebaseAuth.getInstance()
    private val phoneAuth = PhoneAuthProvider.getInstance()
    private var verificationId = ""
    private var phoneNumber = ""
    private lateinit var resendingToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var phoneCredentials: PhoneAuthCredential

    fun getUser() = FirebaseAuth.getInstance().currentUser

    fun verifyPhoneNumber(
        phoneNumber: String,
        signUpActivity: Activity
    ): Observable<PhoneAuthEvent> =
        RxPhoneAuthProvider.verifyPhoneNumber(
            phoneAuth,
            phoneNumber,
            60,
            TimeUnit.SECONDS, signUpActivity
        ).doOnNext {
            if (it is PhoneAuthCodeSentEvent) {
                verificationId = it.verificationId()
                this.phoneNumber = phoneNumber
                resendingToken = it.forceResendingToken()
            } else if (it is PhoneAuthVerificationCompleteEvent) {
                phoneCredentials = it.credential()
            }
        }
            .doOnError { it.printStackTrace() }


    fun resendCode(
        activity: Activity
    ): Observable<PhoneAuthEvent> =
        RxPhoneAuthProvider.verifyPhoneNumber(
            phoneAuth,
            phoneNumber,
            60,
            TimeUnit.SECONDS, activity, resendingToken
        ).doOnNext {
            if (it is PhoneAuthCodeSentEvent) {
                verificationId = it.verificationId()
            }
        }.doOnError { it.printStackTrace() }

    fun signInWithPhone(verificationCode: String) =
        RxFirebaseAuth.signInWithCredential(
            authInstance,
            PhoneAuthProvider.getCredential(verificationId, verificationCode)
        )

    fun signInVerified() =
        RxFirebaseAuth.signInWithCredential(authInstance, phoneCredentials)


}