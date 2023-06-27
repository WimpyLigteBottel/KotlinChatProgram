package org.example.api

import org.example.model.*
import org.example.service.RoomService
import org.example.service.UserInteractionService
import org.example.service.UserService
import org.springframework.web.bind.annotation.*

@RequestMapping("/v1")
@RestController
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
class UserController(
    private val userService: UserService
) {

    @PostMapping("/user")
    fun createUser(user: User): String? {
        return userService.createUser(user);
    }

    @GetMapping("/users")
    fun getUsers(): List<User> {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    fun getUser(@PathVariable id: String): User? {

        return userService.getUser(id)
    }

}

@RestController
@RequestMapping("/v1")
class UserInteractionController(
    private val userInteractionService: UserInteractionService
) {


    @PostMapping("/join")
    fun clientJoinRoom(@RequestBody joinRoomRequest: JoinRoomRequest): String {
        userInteractionService.joinRoom(joinRoomRequest)

        return "success"
    }

    @PostMapping("/message")
    fun sendMessage(@RequestBody messageRequest: MessageRequest): String {
        userInteractionService.sendMessage(messageRequest)

        return "success"
    }

    @PostMapping("/createRoom")
    fun createRoom(@RequestBody createRoomRequest: CreateRoomRequest): String? {
        return userInteractionService.createRoom(createRoomRequest)
    }
}


