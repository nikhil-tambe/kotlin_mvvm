package com.nikhil.suven.app.domain_model

import androidx.room.ColumnInfo

data class Message (
    val message: String,
    val isSender: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)