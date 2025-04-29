package org.example.service.message

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.example.api.message.model.MessageDeleteRequest
import org.example.api.message.model.MessageUpdateRequest
import org.example.api.message.room.MessageRoomRequest
import org.example.dao.message.room.RoomMessageEntity
import org.example.dao.message.room.RoomMessageRepo
import org.example.dao.room.RoomRepo
import org.springframework.stereotype.Service

@Service
class RoomMessageService(
    private val roomRepo: RoomRepo,
    private val roomMessageRepo: RoomMessageRepo,
) {

    suspend fun sendMessage(messageRoomRequest: MessageRoomRequest) = coroutineScope {
        val roomFuture = async { roomRepo.find(messageRoomRequest.toId) }

        val room = roomFuture.await() ?: throw RuntimeException("room not found")

        roomMessageRepo.save(
            RoomMessageEntity(
                fromUserId = messageRoomRequest.fromId,
                toRoomId = room.id!!,
                message = messageRoomRequest.message
            )
        )

        roomRepo.save(room)
    }

    suspend fun updateMessage(messageUpdateRequest: MessageUpdateRequest) = coroutineScope {
        val messageFuture = async { roomMessageRepo.find(messageUpdateRequest.messageId) }

        val message = messageFuture.await() ?: throw RuntimeException("message not found")

        message.message = messageUpdateRequest.message

        roomMessageRepo.save(message)
    }

    suspend fun deleteMessage(deleteMessageRequest: MessageDeleteRequest) = coroutineScope {
        val messageFuture = async { roomMessageRepo.find(deleteMessageRequest.messageId) }

        val message = messageFuture.await() ?: throw RuntimeException("message not found")

        roomMessageRepo.remove(message)
    }

}
