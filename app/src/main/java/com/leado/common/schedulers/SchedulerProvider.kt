/*
 * *
 *  * Created by Ahmed Elshaer on 11/10/19 3:37 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/10/19 1:56 PM
 *
 */

package com.leado.common.schedulers

import com.leado.common.schedulers.BaseSchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProvider : BaseSchedulerProvider {
    override fun io(): Scheduler = Schedulers.io()

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
}
