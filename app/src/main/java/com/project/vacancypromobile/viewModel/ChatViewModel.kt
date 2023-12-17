package com.project.vacancypromobile.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.project.vacancypromobile.models.Message

class ChatViewModel (message: Message) : ViewModel(){


    val date by mutableStateOf(message.showDate())
    val user by mutableStateOf(message.userInitials())
    val userColor by mutableStateOf(message.stringToColor())
    val message by mutableStateOf(message.message)



}
