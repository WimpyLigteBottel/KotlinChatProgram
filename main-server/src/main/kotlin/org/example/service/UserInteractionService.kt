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
            room.messages.add(Message(user, "${user.name}: has joined the room!"))
        }

        roomRepo.save(room)

    }

    fun sendMessage(messageRequest: MessageRequest) {

        val room = roomRepo.find(messageRequest.roomId) ?: throw RuntimeException("room not found")
        val user = userRepo.find(messageRequest.userId) ?: throw RuntimeException("user not found")


        room.messages.add(Message(user, messageRequest.message))

        roomRepo.save(room)
    }

    fun createRoom(createRoomRequest: CreateRoomRequest): String? {

        val user = userRepo.find(createRoomRequest.userId) ?: throw RuntimeException("user not found")


        val room = Room(
            name = createRoomRequest.roomName,
            owner = user,
            users = mutableListOf(user)
        )
        roomRepo.save(room)

        return room.id
    }

    fun deleteRoom(deleteRoomRequest: DeleteRoomRequest) {
        val room = roomRepo.find(deleteRoomRequest.roomId) ?: throw RuntimeException("room not found")
        val user = userRepo.find(deleteRoomRequest.userId) ?: throw RuntimeException("user not found")

        if(room.owner.id != user.id){
            throw RuntimeException("Cant delete this room because user is not the owner")
        }

        roomRepo.remove(room)
    }

}
