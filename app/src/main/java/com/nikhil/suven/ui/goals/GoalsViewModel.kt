package com.nikhil.suven.ui.goals

import android.content.SharedPreferences
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class GoalsViewModel
@ViewModelInject
constructor(
    private val repository: GoalsRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _dateLive = MutableLiveData<Long>()
    val dateLive: LiveData<Long> = _dateLive
    fun setDate(timeInMillis: Long) {
        _dateLive.postValue(timeInMillis)
    }

}