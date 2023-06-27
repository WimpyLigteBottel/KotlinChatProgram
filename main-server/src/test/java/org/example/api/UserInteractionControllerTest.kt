package org.example.api

import org.assertj.core.api.Assertions.assertThat
import org.example.model.CreateRoomRequest
import org.example.model.JoinRoomRequest
import org.example.model.MessageRequest
import org.example.model.User
import org.example.service.RoomRepo
import org.example.service.UserRepo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserInteractionControllerTest {


    @Autowired
    lateinit var userInteractionController: UserInteractionController

    @Autowired
    lateinit var userController: UserController

    @Autowired
    lateinit var roomRepo: RoomRepo

    @Autowired
    lateinit var userRepo: UserRepo


    @BeforeEach
    fun before() {
        roomRepo.removeAll()
        userRepo.removeAll()
    }

    @Test
    fun `user is added to room`() {
        val userId = userController.createUser(User(name = "john"))!!
        val roomId = userInteractionController.createRoom(CreateRoomRequest(userId, "roomname"))!!

        userInteractionController.clientJoinRoom(
            JoinRoomRequest(
                userId = userId,
                roomId = roomId
            )
        )


        val findingUser = roomRepo.find(roomId)?.users?.find { it.id == userId }

        assertThat(findingUser).isNotNull
    }

    @Test
    fun `join message is append if user joins room`() {
        val userId = userController.createUser(User(name = "john"))!!
        val roomId = userInteractionController.createRoom(CreateRoomRequest(userId, "roomname"))!!

        assertThat(roomRepo.find(roomId)?.messages).hasSize(0)

        userInteractionController.clientJoinRoom(
            JoinRoomRequest(
                userId = userId,
                roomId = roomId
            )
        )


        assertThat(roomRepo.find(roomId)?.messages).hasSize(1)
    }

    @Test
    fun `user is added to room and message is added`() {
        val userId = userController.createUser(User(name = "john"))!!
        val roomId = userInteractionController.createRoom(CreateRoomRequest(userId, "roomname"))!!

        userInteractionController.clientJoinRoom(
            JoinRoomRequest(
                userId = userId,
                roomId = roomId
            )
        )


        val findingUser = roomRepo.find(roomId)?.users?.find {
            it.id == userId
        }

        assertThat(findingUser).isNotNull
    }

    @Test
    fun sendMessage() {

        val userId = userController.createUser(User(name = "john"))!!
        val roomId = userInteractionController.createRoom(CreateRoomRequest(userId, "roomname"))!!


        userInteractionController.sendMessage(
            MessageRequest(
                userId,
                roomId,
                "randomMessage"
            )
        )


        assertThat(roomRepo.find(roomId)?.messages).isNotNull
        assertThat(roomRepo.find(roomId)?.messages).hasSize(1)

    }

    @Test
    fun `user is creating a room`() {

        val userId = userController.createUser(User(name = "john"))!!


        val roomId = userInteractionController.createRoom(CreateRoomRequest(userId, "roomname"))


        assertThat(roomRepo.find(roomId!!)).isNotNull

    }
}