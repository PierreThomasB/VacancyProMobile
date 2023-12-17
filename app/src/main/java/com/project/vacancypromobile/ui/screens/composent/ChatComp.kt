package com.project.vacancypromobile.ui.screens.composent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.vacancypromobile.models.Message
import com.project.vacancypromobile.viewModel.ChatViewModel
import java.util.Date

@Composable
fun ChatComp(chatViewModel: ChatViewModel = viewModel()) {
    Box(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                if(chatViewModel.userColor != null && chatViewModel.user != null){
                    ColoredCircleWithInitials(
                        color = chatViewModel.userColor!!,
                        initials = chatViewModel.user!!
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = chatViewModel.message , fontSize = 25.sp)
            }
            Text(text = chatViewModel.date, fontSize = 18.sp , color = Color.Gray)
            Divider(
                modifier = Modifier
                    .fillMaxWidth(2f)
                    .height(1.dp),
                color = Color.LightGray
            )
        }
        }
    }

@Preview
@Composable
fun ChatCompPreview() {
    ChatComp(ChatViewModel(Message(-1, "test" , Date(), "test michel")))
}