package org.example.api.room.model

import org.example.api.user.model.User

data class Room(
    var id: String? = null,
    var name: String = "",
    var owner: User = User(),
    var users: MutableList<User> = mutableListOf()
)

data class JoinRoomRequest(
    val userId: String = "",
    val roomId: String = "",
)


data class CreateRoomRequest(
    val userId: String = "",
    val roomName: String = "",
)

data class DeleteRoomRequest(
    val userId: String = "",
    val roomId: String = "",
)
