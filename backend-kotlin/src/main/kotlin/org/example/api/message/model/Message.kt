package org.example.api.message.model

import org.example.api.user.model.User
import java.time.OffsetDateTime
import java.util.*

data class Message(
    val id: String = UUID.randomUUID().toString(),
    val user: User = User(),
    val message: String = "",
    val dateTime: OffsetDateTime = OffsetDateTime.now()
)