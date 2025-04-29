package org.example.service.message

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.example.api.message.direct.DirectMessageRequest
import org.example.api.message.model.MessageDeleteRequest
import org.example.api.message.model.MessageUpdateRequest
import org.example.dao.message.direct.DirectMessageEntity
import org.example.dao.message.direct.DirectMessageRepo
import org.example.dao.room.RoomRepo
import org.example.dao.user.UserRepo
import org.springframework.stereotype.Service

@Service
class DirectMessageService(
    private val roomRepo: RoomRepo,
    private val userRepo: UserRepo,
    private val messageRepo: DirectMessageRepo,
) {

    suspend fun sendMessage(directMessageRequest: DirectMessageRequest) = coroutineScope {
        val roomFuture = async { roomRepo.find(directMessageRequest.roomIdTo) }
        val userFuture = async { userRepo.find(directMessageRequest.userIdFrom) }

        val room = roomFuture.await() ?: throw RuntimeException("room not found")
        val user = userFuture.await() ?: throw RuntimeException("user not found")

        messageRepo.save(
            DirectMessageEntity(
                fromUserId = directMessageRequest.userIdFrom,
                toUserId = directMessageRequest.roomIdTo,
                message = directMessageRequest.message
            )
        )

        room.users.find { it.id == user.id } ?: throw RuntimeException("User is not in the room!")

        roomRepo.save(room)
    }

    suspend fun updateMessage(messageUpdateRequest: MessageUpdateRequest) = coroutineScope {
        val messageFuture = async { messageRepo.find(messageUpdateRequest.messageId) }

        val message = messageFuture.await() ?: throw RuntimeException("message not found")

        message.message = messageUpdateRequest.message

        messageRepo.save(message)
    }

    suspend fun deleteMessage(deleteMessageRequest: MessageDeleteRequest) = coroutineScope {
        val messageFuture = async { messageRepo.find(deleteMessageRequest.messageId) }

        val message = messageFuture.await() ?: throw RuntimeException("message not found")

        messageRepo.remove(message)
    }
}
