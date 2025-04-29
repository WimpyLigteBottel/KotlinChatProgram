package org.example.api.message.model

data class MessageUpdateRequest(
    val userId: String = "",
    val roomId: String = "",
    val messageId: String = "",
    val message: String = ""
)