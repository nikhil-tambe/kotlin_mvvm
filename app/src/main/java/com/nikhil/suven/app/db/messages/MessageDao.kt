package com.nikhil.suven.app.db.messages

import androidx.room.Dao
import androidx.room.Query
import com.nikhil.suven.app.db.BaseDao

@Dao
abstract class MessageDao : BaseDao<MessageEntity> {

    @Query("select * from messages")
    abstract fun getAllMessages() : List<MessageEntity>

}