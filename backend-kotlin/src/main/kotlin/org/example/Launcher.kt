package org.example

import kotlinx.coroutines.runBlocking
import org.example.api.message.MessageController
import org.example.api.message.model.MessageRequest
import org.example.api.room.RoomController
import org.example.api.room.model.CreateRoomRequest
import org.example.api.room.model.JoinRoomRequest
import org.example.api.user.UserController
import org.example.api.user.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class Launcher : CommandLineRunner {

    @Autowired
    lateinit var userController: UserController

    @Autowired
    lateinit var roomController: RoomController

    @Autowired
    lateinit var messageController: MessageController

    override fun run(vararg args: String?) {
        runBlocking {
            val robId = userController.createUser(User(name = "rob")) ?: throw RuntimeException("Rob not created")
            val bobId = userController.createUser(User(name = "bob")) ?: throw RuntimeException("Bob not created")

            val robRoomId = roomController.createRoom(CreateRoomRequest(robId, "rob room"))
                ?: throw RuntimeException("Rob room not created")
            val bobRoomId = roomController.createRoom(CreateRoomRequest(bobId, "bob room"))
                ?: throw RuntimeException("Bob room not created")


            roomController.clientJoinRoom(JoinRoomRequest(bobId, robRoomId))
            roomController.clientJoinRoom(JoinRoomRequest(robId, bobRoomId))

            messageController.sendMessage(MessageRequest(robId, robRoomId, "Greetings!"))
            messageController.sendMessage(MessageRequest(bobId, bobRoomId, "Greetings!"))

        }
    }
}


fun main() {
    runApplication<Launcher>()
}