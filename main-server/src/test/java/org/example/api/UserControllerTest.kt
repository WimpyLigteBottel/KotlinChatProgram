package org.example.api

import org.assertj.core.api.Assertions.assertThat
import org.example.model.User
import org.example.service.UserRepo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserControllerTest {

    @Autowired
    lateinit var userController: UserController


    @Autowired
    lateinit var userRepo: UserRepo


    @BeforeEach
    fun before(){
        userRepo.removeAll()
    }

    @Test
    fun `user is created in repo`() {
        val createdUserId = userController.createUser(User(name = "ben"))

        assertThat(userRepo.find(createdUserId!!)).isNotNull
    }

    @Test
    fun `able to find all users`() {
        userController.createUser(User(name = "ben"))
        userController.createUser(User(name = "john"))

        assertThat(userController.getUsers()).hasSize(2)
    }

    @Test
    fun `able to find specific user`() {
        userController.createUser(User(name = "ben"))
        val johnId = userController.createUser(User(name = "john"))
        val user = userController.getUser(johnId!!)

        assertThat(user?.id).isNotNull
        assertThat(user?.name).isEqualTo("john")
    }
}