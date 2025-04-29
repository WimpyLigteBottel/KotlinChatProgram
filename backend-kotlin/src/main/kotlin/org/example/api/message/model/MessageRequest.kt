package org.example.api.message.model

data class MessageRequest(
    val userId: String = "",
    val roomId: String = "",
    val message: String = ""
)