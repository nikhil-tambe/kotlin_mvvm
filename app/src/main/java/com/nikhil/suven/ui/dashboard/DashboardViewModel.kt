package com.nikhil.suven.ui.dashboard

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.nikhil.suven.ui.add_purchase.PurchaseRepository

class DashboardViewModel
@ViewModelInject
constructor(
    private val purchaseRepository: PurchaseRepository
) : ViewModel() {

    val list = purchaseRepository.getAllPurchasesLive()

}