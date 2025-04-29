package org.example.api.room

import org.example.api.room.model.CreateRoomRequest
import org.example.api.room.model.DeleteRoomRequest
import org.example.api.room.model.JoinRoomRequest
import org.example.api.room.model.Room
import org.example.service.room.RoomService
import org.springframework.web.bind.annotation.*

@RequestMapping("/v1/rooms")
@RestController
@CrossOrigin
class RoomController(
    private val roomService: RoomService
) {

    @GetMapping
    suspend fun getRoomList(): List<Room> {
        return roomService.getRooms()
    }

    @GetMapping("/{id}")
    suspend fun getRoom(@PathVariable id: String): Room? {
        return roomService.getRoom(id)
    }

    @PostMapping
    suspend fun createRoom(@RequestBody createRoomRequest: CreateRoomRequest): String? {
        return roomService.createRoom(createRoomRequest)
    }

    @DeleteMapping
    suspend fun deleteRoom(@RequestBody deleteRoomRequest: DeleteRoomRequest): String {
        roomService.deleteRoom(deleteRoomRequest)

        return "success"
    }

    @PostMapping("/join")
    suspend fun clientJoinRoom(@RequestBody joinRoomRequest: JoinRoomRequest): String {
        roomService.joinRoom(joinRoomRequest)

        return "success"
    }

}