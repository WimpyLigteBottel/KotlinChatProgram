package org.example

import kotlinx.coroutines.runBlocking
import org.example.api.message.room.RoomMessageController
import org.example.api.message.room.MessageRoomRequest
import org.example.api.room.RoomController
import org.example.api.room.CreateRoomRequest
import org.example.api.room.JoinRoomRequest
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
    lateinit var messageController: RoomMessageController

    override fun run(vararg args: String?) {
        runBlocking {
            val robId = userController.createUser(User(name = "rob"))
            val bobId = userController.createUser(User(name = "bob"))

            val robRoomId = roomController.createRoom(CreateRoomRequest(robId, "rob room"))
                ?: throw RuntimeException("Rob room not created")
            val bobRoomId = roomController.createRoom(CreateRoomRequest(bobId, "bob room"))
                ?: throw RuntimeException("Bob room not created")


            roomController.clientJoinRoom(JoinRoomRequest(bobId, robRoomId))
            roomController.clientJoinRoom(JoinRoomRequest(robId, bobRoomId))

            messageController.sendMessage(MessageRoomRequest(robId, robRoomId, "Greetings!"))
            messageController.sendMessage(MessageRoomRequest(bobId, bobRoomId, "Greetings!"))

        }
    }
}


fun main() {
    runApplication<Launcher>()
}