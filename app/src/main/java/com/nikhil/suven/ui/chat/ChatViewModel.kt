package com.nikhil.suven.ui.chat

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.nikhil.suven.ui.goals.GoalsRepository


class ChatViewModel
@ViewModelInject
constructor(
    repository: ChatRepository
) : ViewModel() {
}