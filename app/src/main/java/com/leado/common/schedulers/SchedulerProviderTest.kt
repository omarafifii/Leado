/*
 * *
 *  * Created by Ahmed Elshaer on 11/10/19 3:37 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 11/10/19 1:58 PM
 *
 */

package com.leado.common.schedulers

import com.leado.common.schedulers.BaseSchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class SchedulerProviderTest : BaseSchedulerProvider {
    override fun io(): Scheduler = Schedulers.trampoline()

    override fun ui(): Scheduler = Schedulers.trampoline()
}
