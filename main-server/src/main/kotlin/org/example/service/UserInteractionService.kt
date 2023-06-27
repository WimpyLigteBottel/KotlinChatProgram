package org.example.service

import org.example.model.*
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserInteractionService(
    private val roomRepo: RoomRepo,
    private val userRepo: UserRepo,
) {


    fun joinRoom(joinRoomRequest: JoinRoomRequest) {

        val room = roomRepo.find(joinRoomRequest.roomId) ?: throw RuntimeException("room not found")
        val user = userRepo.find(joinRoomRequest.userId) ?: throw RuntimeException("user not found")


        val userDoesNotExist = room.users.find { it.id == joinRoomRequest.userId } == null

        if (userDoesNotExist) {
            room.users.add(user)
        }


        room.messages.add(Message(user, "Has joined the room ${user.name}"))
    }

    fun sendMessage(messageRequest: MessageRequest) {

        val room = roomRepo.find(messageRequest.roomId) ?: throw RuntimeException("room not found")
        val user = userRepo.find(messageRequest.userId) ?: throw RuntimeException("user not found")


        room.messages.add(Message(user, messageRequest.message))
    }

    fun createRoom(createRoomRequest: CreateRoomRequest): String? {

        val user = userRepo.find(createRoomRequest.userId) ?: throw RuntimeException("user not found")


        val room = Room(
            id = UUID.randomUUID().toString(),
            name = createRoomRequest.roomName,
            owner = user
        )
        roomRepo.rooms.add(room)

        return room.id
    }

}
