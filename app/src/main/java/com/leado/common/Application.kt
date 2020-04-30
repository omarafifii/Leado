package com.leado.common

import android.app.Application
import android.os.StrictMode
import com.instabug.library.Instabug
import com.leado.BuildConfig
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

        enableStrictMode()

    }

    /**
     * DEBUG
     * **/
    private fun enableStrictMode() {
        if (BuildConfig.DEBUG
        /**using only in Debug Mode**/
        ) {
            /**Create StrictMode Policy**/
            val policy = StrictMode.ThreadPolicy.Builder()
                //.detectDiskReads().detectDiskWrites().detectNetwork()
                .detectAll()
                //.penaltyDialog().penaltyDeath()
                .penaltyLog()
                /**We can get multi penalty for one policy **/
                .build();

            /**Set StrictMode Policy **/
            StrictMode.setThreadPolicy(policy);
        }
    }
}