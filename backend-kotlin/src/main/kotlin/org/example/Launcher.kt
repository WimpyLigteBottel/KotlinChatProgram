package org.example

import kotlinx.coroutines.runBlocking
import org.example.api.RoomController
import org.example.api.UserController
import org.example.api.UserInteractionController
import org.example.model.CreateRoomRequest
import org.example.model.JoinRoomRequest
import org.example.model.MessageRequest
import org.example.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class Launcher : CommandLineRunner {

    @Autowired
    lateinit var roomController: RoomController

    @Autowired
    lateinit var userController: UserController

    @Autowired
    lateinit var userInteractionController: UserInteractionController

    override fun run(vararg args: String?) {
        runBlocking {
            val robId = userController.createUser(User(name = "rob"))
            val bobId = userController.createUser(User(name = "bob"))


            val robRoomId = userInteractionController.createRoom(CreateRoomRequest(robId!!, "rob room"))
            val bobRoomId = userInteractionController.createRoom(CreateRoomRequest(bobId!!, "bob room"))


            userInteractionController.clientJoinRoom(JoinRoomRequest(bobId, robRoomId!!))
            userInteractionController.clientJoinRoom(JoinRoomRequest(robId, bobRoomId!!))

            userInteractionController.sendMessage(MessageRequest(robId, robRoomId, "Greetings!"))
            userInteractionController.sendMessage(MessageRequest(bobId, bobRoomId, "Greetings!"))

        }
    }
}


fun main() {
    runApplication<Launcher>()
}