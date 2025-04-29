package org.example.service.message

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.example.api.message.direct.DirectMessageRequest
import org.example.dao.RoomRepo
import org.example.dao.UserRepo
import org.example.dao.message.DirectMessageRepo
import org.example.dao.message.entity.DirectMessageEntity
import org.springframework.stereotype.Service

@Service
class DirectMessageService(
    private val roomRepo: RoomRepo,
    private val userRepo: UserRepo,
    private val messageRepo: DirectMessageRepo,
) {

    suspend fun sendMessage(directMessageRequest: DirectMessageRequest) = coroutineScope {
        val roomFuture = async {
            roomRepo.find(directMessageRequest.roomIdTo) ?: throw RuntimeException("room not found")
        }
        val userFuture = async {
            userRepo.find(directMessageRequest.userIdFrom) ?: throw RuntimeException("user not found")
        }

        val room = roomFuture.await()
        val user = userFuture.await()

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
}
