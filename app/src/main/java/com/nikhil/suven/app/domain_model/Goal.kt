package com.nikhil.suven.app.domain_model

data class Goal(
    val dateCreated: Long = System.currentTimeMillis(),
    val forWhom: Int,
    val isVariable: Boolean,
    val dateOfPurchase: Long,
    val numberOfUnit: Int,
    val purchasePrice: Float
)