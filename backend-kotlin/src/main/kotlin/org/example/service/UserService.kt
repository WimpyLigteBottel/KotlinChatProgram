package org.example.service

import org.example.model.User
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepo: UserRepo
) {
    suspend fun createUser(user: User) = userRepo.save(user)

    suspend fun getUsers(): List<User> = userRepo.findAll()

    suspend fun getUser(id: String) = userRepo.find(id)

}


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