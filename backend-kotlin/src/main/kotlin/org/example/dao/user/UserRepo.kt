package org.example.dao.user

import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserRepo {
    private var users: MutableList<UserEntity> = ArrayList(1000)

    suspend fun findAll(): List<UserEntity> {
        return users.toList()
    }

    suspend fun find(id: String): UserEntity? {
        return users.find { it.id == id }
    }

    suspend fun removeAll() {
        users = mutableListOf()
    }

    suspend fun remove(user: UserEntity) {
        users = users
            .filter { it.id != user.id }
            .toMutableList()
    }

    suspend fun save(user: UserEntity): String {

        val element = user.copy(id = UUID.randomUUID().toString())
        users.add(element)

        return element.id
    }
}