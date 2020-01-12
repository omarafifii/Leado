package com.leado.common

import com.leado.common.schedulers.SchedulerProvider
import com.leado.data.sources.FirebaseAuthManager
import com.leado.ui.phoneverification.VerificationViewModel
import com.leado.ui.signup.AuthRepository
import com.leado.ui.signup.SignUpViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {
    single { FirebaseAuthManager() }
    single { AuthRepository(get()) }

    viewModel { SignUpViewModel(get(), SchedulerProvider()) }
    viewModel { VerificationViewModel(get(), SchedulerProvider()) }

}