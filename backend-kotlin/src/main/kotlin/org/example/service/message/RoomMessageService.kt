package org.example.service.message

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.example.api.message.model.MessageDeleteRequest
import org.example.api.message.room.MessageRoomRequest
import org.example.api.message.model.MessageUpdateRequest
import org.example.dao.RoomRepo
import org.example.dao.UserRepo
import org.example.dao.message.RoomMessageRepo
import org.example.dao.message.entity.RoomMessageEntity
import org.springframework.stereotype.Service
import java.time.OffsetDateTime
import java.util.*

@Service
class RoomMessageService(
    private val roomRepo: RoomRepo,
    private val userRepo: UserRepo,
    private val roomMessageRepo: RoomMessageRepo,
) {

    suspend fun sendMessage(messageRoomRequest: MessageRoomRequest) = coroutineScope {
        val roomFuture = async {
            roomRepo.find(messageRoomRequest.toId) ?: throw RuntimeException("room not found")
        }
        val userFuture = async {
            userRepo.find(messageRoomRequest.fromId) ?: throw RuntimeException("user not found")
        }

        val room = roomFuture.await()
        val user = userFuture.await()

        room.users.find { it.id == user.id } ?: throw RuntimeException("User is not in the room!")

        roomMessageRepo.save(
            RoomMessageEntity(
                UUID.randomUUID().toString(),
                created = OffsetDateTime.now(),
                fromUserId = messageRoomRequest.fromId,
                toRoomId = messageRoomRequest.toId,
                message = messageRoomRequest.message
            )
        )

        roomRepo.save(room)
    }

    suspend fun updateMessage(messageUpdateRequest: MessageUpdateRequest) = coroutineScope {
        val messageFuture =
            async {
                roomMessageRepo.find(messageUpdateRequest.messageId) ?: throw RuntimeException("message not found")
            }

        val message = messageFuture.await()

        message.message = messageUpdateRequest.message

        roomMessageRepo.save(message)
    }

    suspend fun deleteMessage(deleteMessageRequest: MessageDeleteRequest) = coroutineScope {
        val messageFuture =
            async {
                roomMessageRepo.find(deleteMessageRequest.messageId) ?: throw RuntimeException("message not found")
            }

        val message = messageFuture.await()

        roomMessageRepo.remove(message)
    }

}
