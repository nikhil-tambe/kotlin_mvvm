package com.nikhil.suven.app.db.messages

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.nikhil.suven.app.db.BaseDao

@Dao
abstract class MessageDao : BaseDao<MessageEntity> {

    @Query("select * from messages order by time_stamp desc")
    abstract fun getAllMessages() : LiveData<List<MessageEntity>>

}