package org.example.api.user

import org.example.api.user.model.User
import org.example.service.user.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/v1/users")
@RestController
@CrossOrigin
class UserController(
    private val userService: UserService
) {

    @GetMapping
    suspend fun getUsers(): List<User> {
        return userService.getUsers()
    }

    @PostMapping
    suspend fun createUser(@RequestBody user: User): String? {
        return userService.createUser(user);
    }


    @GetMapping("/{id}")
    suspend fun getUser(@PathVariable id: String): User? {
        return userService.getUser(id)
    }

    @DeleteMapping("/{id}")
    suspend fun deleteUser(@PathVariable id: String): ResponseEntity<String> {
        userService.deleteUser(id)

        return ResponseEntity.accepted().build()
    }

}