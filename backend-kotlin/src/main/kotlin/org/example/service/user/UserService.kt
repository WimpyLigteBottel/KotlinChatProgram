package org.example.service.user

import org.example.api.user.model.User
import org.example.dao.user.UserEntity
import org.example.dao.user.UserRepo
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepo: UserRepo
) {

    suspend fun getUsers() = userRepo.findAll()

    suspend fun getUser(id: String) = userRepo.find(id)

    suspend fun createUser(user: User) = userRepo.save(UserEntity(name = user.name))

    suspend fun deleteUser(id: String) = userRepo.find(id)?.let { userRepo.remove(it) }

}