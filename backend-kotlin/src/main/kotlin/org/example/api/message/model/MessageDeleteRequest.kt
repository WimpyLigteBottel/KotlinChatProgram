package org.example.api.message.model

data class MessageDeleteRequest(
    val userId: String = "",
    val roomId: String = "",
    val messageId: String = ""
)