package com.nikhil.suven.ui.chat

import android.text.format.DateUtils
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.nikhil.suven.app.db.messages.CacheMessageMapper
import com.nikhil.suven.app.db.messages.MessageDao
import com.nikhil.suven.app.db.messages.MessageEntity
import com.nikhil.suven.app.domain_model.Message


class ChatRepository
constructor(
    private val messageDao: MessageDao,
    private val cacheMessageMapper: CacheMessageMapper
) {

    fun getLiveChats() = liveData<List<Message>> {
        emitSource(
            messageDao.getAllMessages()
                .map {
                    cacheMessageMapper.mapFromEntityList(it)
                }
        )
    }

    suspend fun insertMessage(messageEntity: MessageEntity) {
        messageDao.insert(messageEntity)
    }

    suspend fun insertDummyMessages() {
        val now = System.currentTimeMillis()
        insertMessage(MessageEntity(
            timestamp = now.minus(DateUtils.MINUTE_IN_MILLIS * 5),
            isUser = true,
            message = "Hello"
        ))
        insertMessage(MessageEntity(
            timestamp = now.minus(DateUtils.MINUTE_IN_MILLIS * 4),
            isUser = false,
            message = "Hello! Welcome to this app.\nEnjoy our features."
        ))
        insertMessage(MessageEntity(
            timestamp = now.minus(DateUtils.MINUTE_IN_MILLIS * 3),
            isUser = false,
            message = "Hope You Like Them"
        ))
        insertMessage(MessageEntity(
            timestamp = now.minus(DateUtils.MINUTE_IN_MILLIS * 2),
            isUser = true,
            message = "Okay.."
        ))
    }

}