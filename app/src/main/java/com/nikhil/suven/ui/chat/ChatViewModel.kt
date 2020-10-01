package com.nikhil.suven.ui.chat

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhil.suven.app.db.messages.MessageEntity
import kotlinx.coroutines.launch

class ChatViewModel
@ViewModelInject
constructor(
    private val repository: ChatRepository
) : ViewModel() {

    val messageText = MutableLiveData<String>()

    val list = repository.getLiveChats()

    fun insertDummyMessages() = viewModelScope.launch {
        repository.insertDummyMessages()
    }

    fun insertMessage() = viewModelScope.launch {
        val s = messageText.value
        if (s.isNullOrEmpty()) {
            return@launch
        }
        val messageEntity = MessageEntity(
            timestamp = System.currentTimeMillis(),
            isUser = true,
            message = s
        )
        repository.insertMessage(messageEntity)
        messageText.postValue("")
    }

}