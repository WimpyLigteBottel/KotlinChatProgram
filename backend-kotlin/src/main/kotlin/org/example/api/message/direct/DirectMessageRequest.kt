package org.example.api.message.direct

data class DirectMessageRequest(
    val userIdFrom: String = "",
    val roomIdTo: String = "",
    val message: String = ""
)

