package org.example.service.room

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.example.api.message.room.MessageRoomRequest
import org.example.api.room.CreateRoomRequest
import org.example.api.room.DeleteRoomRequest
import org.example.api.room.JoinRoomRequest
import org.example.api.room.Room
import org.example.dao.room.RoomRepo
import org.example.dao.user.UserRepo
import org.example.service.message.RoomMessageService
import org.springframework.stereotype.Service
import java.util.*

@Service
class RoomService(
    private val roomRepo: RoomRepo,
    private val userRepo: UserRepo,
    private val roomMessageService: RoomMessageService
) {

    suspend fun getRooms(): List<Room> {
        return roomRepo.findAll()
    }


    suspend fun getRoom(id: String): Room? {
        return roomRepo.find(id)
    }

    suspend fun joinRoom(joinRoomRequest: JoinRoomRequest) {

        val room = roomRepo.find(joinRoomRequest.roomId) ?: throw RuntimeException("room not found")
        val user = userRepo.find(joinRoomRequest.userId) ?: throw RuntimeException("user not found")

        val userDoesNotExist = room.users.find { it.id == joinRoomRequest.userId } == null

        if (userDoesNotExist) {
            room.users.add(user)

            roomMessageService.sendMessage(
                MessageRoomRequest(
                    fromId = "TODO-SERVER-ID",
                    toId = joinRoomRequest.roomId,
                    message = "has joined the room!"
                )
            )
        }

        roomRepo.save(room)

    }

    suspend fun createRoom(createRoomRequest: CreateRoomRequest): String? {

        val user = userRepo.find(createRoomRequest.userId) ?: throw RuntimeException("user not found")

        val room = Room(
            name = createRoomRequest.roomName,
            ownerId = user.id,
            users = mutableListOf(user)
        )

        val roomId = roomRepo.save(room)

        roomMessageService.sendMessage(
            MessageRoomRequest(
                fromId = "TODO-SERVER-ID",
                toId = roomId,
                message = "has joined the room!"
            )
        )

        return roomId
    }

    suspend fun deleteRoom(deleteRoomRequest: DeleteRoomRequest) = coroutineScope {
        val roomFuture = async {
            roomRepo.find(deleteRoomRequest.roomId) ?: throw RuntimeException("room not found")
        }
        val userFuture = async {
            userRepo.find(deleteRoomRequest.userId) ?: throw RuntimeException("user not found")
        }

        val room = roomFuture.await()
        val user = userFuture.await()

        if (room.ownerId != user.id) {
            throw RuntimeException("Cant delete this room because user is not the owner")
        }

        roomRepo.remove(room)
    }

}
