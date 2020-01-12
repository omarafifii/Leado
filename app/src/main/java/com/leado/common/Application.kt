package com.leado.common

import android.app.Application
import com.instabug.library.Instabug
import com.leado.R
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(applicationModule)
        }
        Instabug.Builder(this, getString(R.string.instabug_api)).build()



    }
}