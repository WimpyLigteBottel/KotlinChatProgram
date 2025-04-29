package org.example.service.message

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.example.api.message.model.Message
import org.example.api.message.model.MessageDeleteRequest
import org.example.api.message.model.MessageRequest
import org.example.api.message.model.MessageUpdateRequest
import org.example.dao.RoomRepo
import org.example.dao.UserRepo
import org.springframework.stereotype.Service
import java.util.*

@Service
class MessageService(
    private val roomRepo: RoomRepo,
    private val userRepo: UserRepo,
) {

    suspend fun sendMessage(messageRequest: MessageRequest) = coroutineScope {
        val roomFuture = async {
            roomRepo.find(messageRequest.roomId) ?: throw RuntimeException("room not found")
        }
        val userFuture = async {
            userRepo.find(messageRequest.userId) ?: throw RuntimeException("user not found")
        }

        val room = roomFuture.await()
        val user = userFuture.await()

        room.users.find { it.id == user.id } ?: throw RuntimeException("User is not in the room!")


        room.messages.add(Message(UUID.randomUUID().toString(), user, messageRequest.message))

        roomRepo.save(room)
    }

    suspend fun updateMessage(messageUpdateRequest: MessageUpdateRequest) = coroutineScope {
        val roomFuture =
            async { roomRepo.find(messageUpdateRequest.roomId) ?: throw RuntimeException("room not found") }
        val userFuture =
            async { userRepo.find(messageUpdateRequest.userId) ?: throw RuntimeException("user not found") }

        val room = roomFuture.await()
        val user = userFuture.await()

        room.users.find { it.id == user.id } ?: throw RuntimeException("User is not in the room!")

        room.messages.add(Message(UUID.randomUUID().toString(), user, messageUpdateRequest.message))

        roomRepo.save(room)
    }

    suspend fun deleteMessage(deleteMessageRequest: MessageDeleteRequest) = coroutineScope {
        val roomFuture =
            async { roomRepo.find(deleteMessageRequest.roomId) ?: throw RuntimeException("room not found") }
        val userFuture =
            async { userRepo.find(deleteMessageRequest.userId) ?: throw RuntimeException("user not found") }

        val room = roomFuture.await()
        val user = userFuture.await()

        room.users.find { it.id == user.id } ?: throw RuntimeException("User is not in the room!")

        room.messages.removeIf { it.id == deleteMessageRequest.messageId }

        roomRepo.save(room)
    }

}
