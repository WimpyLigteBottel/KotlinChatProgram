package org.example.service.user

import org.example.dao.UserRepo
import org.example.api.user.model.User
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepo: UserRepo
) {

    suspend fun getUsers(): List<User> = userRepo.findAll()

    suspend fun getUser(id: String) = userRepo.find(id)

    suspend fun createUser(user: User) = userRepo.save(user)

    suspend fun deleteUser(id: String) = userRepo.find(id)?.let { userRepo.remove(it) }

}