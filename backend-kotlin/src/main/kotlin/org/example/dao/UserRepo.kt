package org.example.dao

import org.example.api.user.model.User
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserRepo {

    var users: MutableList<User> = ArrayList(1000)


    suspend fun findAll(): List<User> {
        return users.toList()
    }

    suspend fun find(id: String): User? {
        return users.find { it.id == id }
    }

    suspend fun removeAll() {
        users = mutableListOf()
    }

    suspend fun remove(user: User) {
        users = users.filter {
            it.id != user.id
        }.toMutableList()
    }

    suspend fun save(user: User): String? {

        if (user.id == null) {
            user.id = UUID.randomUUID().toString()
            users.add(user)

            return user.id
        }

        user.id?.let {
            var foundRoom = find(it)
            foundRoom = user

        }

        return user.id

    }
}