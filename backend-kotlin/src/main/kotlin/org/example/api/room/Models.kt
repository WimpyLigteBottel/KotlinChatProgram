package org.example.api.room

import org.example.dao.user.UserEntity

data class Room(
    var id: String? = null,
    var name: String = "",
    var ownerId: String,
    var users: MutableList<UserEntity> = mutableListOf()
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
