package com.nikhil.suven.ui.goals

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhil.suven.R
import com.nikhil.suven.app.domain_model.Goal
import com.nikhil.suven.utils.DataState
import com.nikhil.suven.utils.Event
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class GoalsViewModel
@ViewModelInject
constructor(
    private val repository: GoalsRepository
) : ViewModel() {

    /**
     * Throws messages on screen via a Snackbar
     * */
    private val _snackbarText = MutableLiveData<Event<Any>>()
    val snackbarText: LiveData<Event<Any>> = _snackbarText

    /**
     * Keeps track of drop down list selection
     * */
    var forWhom = 0

    /**
     * Keeps track of SwitchCompact button
     * */
    var isVariable = MutableLiveData<Boolean>().apply {
        value = false
    }

    /**
     * Keeps track of date selected from dialog
     * Needs to be mutable as it changes across fragments
     * */
    private val _dateLive = MutableLiveData<Long>()
    val dateLive: LiveData<Long> = _dateLive
    fun setDate(timeInMillis: Long) {
        _dateLive.postValue(timeInMillis)
    }

    /**
     * Keeps track of number of units in form
     * */
    val numberOfUnits = MutableLiveData<String>()

    /**
     * Keeps track of purchase price in form
     * */
    var purchasePrice = MutableLiveData<String>()

    /**
     * Adds a new purchase if all conditions are satisfied
     * */
    fun addPurchase() {
        val currentDateLive = _dateLive.value
        val units = numberOfUnits.value
        val price = purchasePrice.value
        val currentIsVariable = isVariable.value
        if (currentDateLive == null || units == null || currentIsVariable == null || price == null) {
            _snackbarText.value = Event(R.string.blank_form_error)
            return
        }
        if (forWhom == 0) {
            _snackbarText.value = Event(R.string.select_a_member)
            return
        }
        if (units.toInt() == 0 || price.toFloat() == 0f) {
            _snackbarText.value = Event(R.string.zero_values)
            return
        }
        val goal = Goal(
            forWhom = forWhom,
            isVariable = currentIsVariable,
            purchasePrice = price.toFloat(),
            numberOfUnit = units.toInt(),
            dateOfPurchase = currentDateLive
        )
        viewModelScope.launch {
            repository.addNewGoal(goal)
                .onEach { dataState ->
                    if (dataState is DataState.Success) {
                        clearFields()
                        _snackbarText.value = Event(R.string.purchase_added_success)
                    } else if (dataState is DataState.Error) {
                        _snackbarText.value = Event(dataState.e)
                    }
                }.launchIn(viewModelScope)
        }
    }

    private fun clearFields() {

    }

    /**
     * Keeps track of total purchases in DB and updates count on screen
     * */
    val totalPurchases = repository.getTotalPurchaseCount()

}