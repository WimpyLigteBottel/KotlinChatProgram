package org.example.dao.room

import org.example.api.user.model.User

data class RoomEntity(
    var id: String? = null,
    var name: String = "",
    var owner: User = User(),
    var users: MutableList<User> = mutableListOf()
)