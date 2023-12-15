package com.project.vacancypromobile.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.vacancypromobile.ui.screens.composent.ChatComp
import com.project.vacancypromobile.viewModel.ChatViewModel

@Composable
fun ChatListScreen(chatViewModel: ChatViewModel = viewModel()) {
    Box {
        Text("Chat")
        Column {
            for(message in chatViewModel.messages) {
                ChatComp()
            }
            Row( Modifier.padding(8.dp)) {

                OutlinedTextField(value = chatViewModel.chatTemp, onValueChange = {chatViewModel.sendMessage()})
                Button(onClick = { chatViewModel.sendMessage() }) {
                    Icon(Icons.Default.Send, contentDescription = "Envoyer")
                }
            }
        }
    }

}