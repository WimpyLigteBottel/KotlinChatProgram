package org.example.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import org.example.model.*
import org.springframework.stereotype.Service

@Service
class UserInteractionService(
    private val roomRepo: RoomRepo,
    private val userRepo: UserRepo,
) {


    suspend fun joinRoom(joinRoomRequest: JoinRoomRequest) {

        val room = roomRepo.find(joinRoomRequest.roomId) ?: throw RuntimeException("room not found")
        val user = userRepo.find(joinRoomRequest.userId) ?: throw RuntimeException("user not found")


        val userDoesNotExist = room.users.find { it.id == joinRoomRequest.userId } == null

        if (userDoesNotExist) {
            room.users.add(user)
            room.messages.add(Message(user, "has joined the room!"))
        }

        roomRepo.save(room)

    }

    suspend fun sendMessage(messageRequest: MessageRequest) {
        withContext(Dispatchers.IO) {

            val roomFuture = async {
                roomRepo.find(messageRequest.roomId) ?: throw RuntimeException("room not found")
            }
            val userFuture = async {
                userRepo.find(messageRequest.userId) ?: throw RuntimeException("user not found")
            }

            val room = roomFuture.await()
            val user = userFuture.await()

            room.users.find { it.id == user.id } ?: throw RuntimeException("User is not in the room!")


            room.messages.add(Message(user, messageRequest.message))

            roomRepo.save(room)
        }
    }

    suspend fun createRoom(createRoomRequest: CreateRoomRequest): String? {

        val user = userRepo.find(createRoomRequest.userId) ?: throw RuntimeException("user not found")

        val room = Room(
            name = createRoomRequest.roomName,
            owner = user,
            users = mutableListOf(user)
        )

        room.messages.add(Message(user, "Has created the room"))

        roomRepo.save(room)

        return room.id
    }

    suspend fun deleteRoom(deleteRoomRequest: DeleteRoomRequest) {
        withContext(Dispatchers.IO) {
            val roomFuture = async {
                roomRepo.find(deleteRoomRequest.roomId) ?: throw RuntimeException("room not found")
            }
            val userFuture = async {
                userRepo.find(deleteRoomRequest.userId) ?: throw RuntimeException("user not found")
            }

            val room = roomFuture.await()
            val user = userFuture.await()

            if (room.owner.id != user.id) {
                throw RuntimeException("Cant delete this room because user is not the owner")
            }

            roomRepo.remove(room)
        }
    }

}
