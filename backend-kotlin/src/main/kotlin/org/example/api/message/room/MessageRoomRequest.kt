package org.example.api.message.room

data class MessageRoomRequest(
    val fromId: String = "",
    val toId: String = "",
    val message: String = ""
)

