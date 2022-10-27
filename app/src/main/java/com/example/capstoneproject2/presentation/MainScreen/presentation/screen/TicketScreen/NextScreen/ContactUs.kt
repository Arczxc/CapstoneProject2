package com.example.capstoneproject2.presentation.MainScreen.presentation.screen.TicketScreen.NextScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.capstoneproject2.R


data class Chat(
    val message: String,
    val time: String,
    val isOutgoing: Boolean
)

val message = mutableStateOf("")

val chats = mutableStateListOf<Chat>(
    Chat("Hi", "10:00 pm", true),
    Chat("Welcome to Customer Service. My name is J .", "10:00 pm", false),
    Chat("How can I help you?", "10:02 pm", false),
    Chat("May I ask How can I track my order", "10:02 pm", true),
    Chat("Okay hold on a second", "10:02 pm", false),
)

const val username = "Support Agent"
const val profile = R.drawable.support_agent
const val isOnline = true


@Composable
fun ContactUs(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopBarSection(
            username = username,
            profile = painterResource(id = profile),
            isOnline = isOnline,
            onBack = { /*navController.navigateUp()*/ }
        )
        ChatSection(Modifier.weight(1f))
        MessageSection()
    }
}

@Composable
fun TopBarSection(
    username: String,
    profile: Painter,
    isOnline: Boolean,
    onBack: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        backgroundColor = Color(0xFFFAFAFA),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBack
            ){
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }


            Spacer(modifier = Modifier.width(8.dp))

            Image(
                painter = profile,
                contentDescription = null,
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(text = username, fontWeight = FontWeight.SemiBold)
                Text(
                    text = if (isOnline) "Online" else "Offline",
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun ChatSection(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        items(chats) { chat ->
            MessageItem(
                chat.message,
                chat.time,
                chat.isOutgoing
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun MessageSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = Color.White,
        elevation = 10.dp
    ) {
        OutlinedTextField(
            placeholder = {
                Text("Message..")
            },
            value = message.value,
            onValueChange = {
                message.value = it
            },
            shape = RoundedCornerShape(25.dp),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.send, ),
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier.clickable {
                        chats.add(Chat(message.value, "10:00 PM", true))
                        message.value = ""
                    }
                )

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
        )
    }
}

@Composable
fun MessageItem(
    messageText: String,
    time: String,
    isOut: Boolean
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (isOut) Alignment.End else Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .background(
                    if (isOut) MaterialTheme.colors.primary else Color(0xFF616161),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(
                    top = 8.dp,
                    bottom = 8.dp,
                    start = 16.dp,
                    end = 16.dp
                )
        ) {
            Text(
                text = messageText,
                color = Color.White
            )
        }

        Text(
            text = time,
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}