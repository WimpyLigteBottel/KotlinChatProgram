package org.example.api

import org.example.model.*
import org.example.service.RoomService
import org.example.service.UserInteractionService
import org.example.service.UserService
import org.springframework.web.bind.annotation.*


@RequestMapping("/v1")
@RestController
@CrossOrigin
class RoomController(
    private val roomService: RoomService
) {

    @GetMapping("/rooms")
    fun getRoomList(): List<Room> {
        return roomService.getRooms()
    }

    @GetMapping("/rooms/{id}")
    fun getRoom(@PathVariable id: String): Room? {
        return roomService.getRoom(id)
    }


}

@RequestMapping("/v1")
@RestController
@CrossOrigin
class UserController(
    private val userService: UserService
) {

    @PostMapping("/users")
    suspend fun createUser(@RequestBody user: User): String? {
        return userService.createUser(user);
    }

    @GetMapping("/users")
    suspend fun getUsers(): List<User> {
        return userService.getUsers()
    }

    @GetMapping("/users/{id}")
    suspend fun getUser(@PathVariable id: String): User? {
        return userService.getUser(id)
    }

}

@RestController
@RequestMapping("/v1")
@CrossOrigin
class UserInteractionController(
    private val userInteractionService: UserInteractionService
) {


    @PostMapping("/join")
    suspend fun clientJoinRoom(@RequestBody joinRoomRequest: JoinRoomRequest): String {
        userInteractionService.joinRoom(joinRoomRequest)

        return "success"
    }

    @PostMapping("/message")
    suspend fun sendMessage(@RequestBody messageRequest: MessageRequest): String {
        userInteractionService.sendMessage(messageRequest)

        return "success"
    }

    @PostMapping("/createRoom")
    suspend fun createRoom(@RequestBody createRoomRequest: CreateRoomRequest): String? {
        return userInteractionService.createRoom(createRoomRequest)
    }

    @DeleteMapping("/deleteRoom")
    suspend fun deleteRoom(@RequestBody deleteRoomRequest: DeleteRoomRequest): String {
        userInteractionService.deleteRoom(deleteRoomRequest)

        return "success"
    }
}


