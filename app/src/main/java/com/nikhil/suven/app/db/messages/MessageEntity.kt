package com.nikhil.suven.app.db.messages

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class MessageEntity (

    @PrimaryKey()
    @ColumnInfo(name = "time_stamp")
    val timestamp: Long,

    @ColumnInfo(name = "message")
    val message: String,

    @ColumnInfo(name = "is_sender")
    val isSender: Boolean

)