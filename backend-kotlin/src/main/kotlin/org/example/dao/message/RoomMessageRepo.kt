package org.example.dao.message

import org.example.dao.message.entity.RoomMessageEntity
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class RoomMessageRepo {

    private var roomMessages: MutableList<RoomMessageEntity> = ArrayList(1000)

    suspend fun findAll(): List<RoomMessageEntity> {
        return roomMessages.toList()
    }

    suspend fun find(id: String): RoomMessageEntity? {
        return roomMessages.find { it.id == id }
    }

    suspend fun removeAll() {
        roomMessages = mutableListOf()
    }

    suspend fun remove(user: RoomMessageEntity) {
        roomMessages = roomMessages.filter {
            it.id != user.id
        }.toMutableList()
    }

    suspend fun save(messageEntity: RoomMessageEntity): String {
        if (messageEntity.id == null) {
            messageEntity.id = UUID.randomUUID().toString()
            roomMessages.add(messageEntity)

            return messageEntity.id
        }

        return messageEntity.id
    }
}