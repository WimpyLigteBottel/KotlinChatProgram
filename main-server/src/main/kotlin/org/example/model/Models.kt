package org.example.model

import java.time.OffsetDateTime

data class User(var id: String? = null, var name: String)


data class Room(
    var id: String? = null,
    var name: String,
    var owner: User,
    var users: MutableList<User> = mutableListOf(),
    var messages: MutableList<Message> = mutableListOf()
)

data class JoinRoomRequest(
    val userId: String,
    val roomId: String,
)


data class MessageRequest(
    val userId: String,
    val roomId: String,
    val message: String
)


data class CreateRoomRequest(
    val userId: String,
    val roomName: String
)

data class Message(
    val user: User,
    val message: String,
    val dateTime: OffsetDateTime = OffsetDateTime.now()
)

