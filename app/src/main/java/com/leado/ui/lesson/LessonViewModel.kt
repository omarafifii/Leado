package com.leado.ui.lesson

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LessonViewModel: ViewModel() {

     var startVideoSeconds =0f

protected val _showCompleteIcon :MutableLiveData<Boolean> = MutableLiveData()
    val showCompleteIcon: LiveData<Boolean>  get() = _showCompleteIcon



}