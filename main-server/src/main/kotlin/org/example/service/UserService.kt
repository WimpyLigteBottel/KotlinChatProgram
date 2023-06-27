package org.example.service

import org.example.model.User
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepo: UserRepo
) {
    fun createUser(user: User): String? {
        return userRepo.save(user)
    }

    fun getUsers(): List<User> = userRepo.findAll()

    fun getUser(id: String) = userRepo.find(id)

}


@Repository
class UserRepo {

    var users: MutableList<User> = mutableListOf()


    fun findAll(): List<User> {
        return users.toList()
    }

    fun find(id: String): User? {
        return users.find { it.id == id }
    }

    fun removeAll() {
        users = mutableListOf()
    }

    fun remove(user: User) {
        users = users.filter {
            it.id != user.id
        }.toMutableList()
    }

    fun save(user: User): String? {

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