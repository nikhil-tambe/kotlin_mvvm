package com.nikhil.suven.ui.chat

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhil.suven.app.db.messages.MessageEntity
import kotlinx.coroutines.launch

class ChatViewModel
@ViewModelInject
constructor(
    private val repository: ChatRepository
) : ViewModel() {

    val list = repository.getLiveChats()

    fun insertDummyMessages() = viewModelScope.launch {
        repository.insertDummyMessages()
    }

    suspend fun insertMessage(s: String) = viewModelScope.launch {
        val messageEntity = MessageEntity(
            timestamp = System.currentTimeMillis(),
            isUser = true,
            message = s
        )
        repository.insertMessage(messageEntity)
    }

}