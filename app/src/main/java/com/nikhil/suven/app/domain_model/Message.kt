package com.nikhil.suven.app.domain_model

import androidx.room.ColumnInfo

data class Message (
    val message: String,
    val isOwner: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)