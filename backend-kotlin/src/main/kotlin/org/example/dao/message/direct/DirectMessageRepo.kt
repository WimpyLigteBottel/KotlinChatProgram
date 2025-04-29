package org.example.dao.message.direct

import org.springframework.stereotype.Repository
import java.util.*

@Repository
class DirectMessageRepo {

    var messages: MutableList<DirectMessageEntity> = ArrayList(1000)

    suspend fun findAll(): List<DirectMessageEntity> {
        return messages.toList()
    }

    suspend fun find(id: String): DirectMessageEntity? {
        return messages.find { it.id == id }
    }

    suspend fun removeAll() {
        messages = mutableListOf()
    }

    suspend fun remove(directMessageEntity: DirectMessageEntity) {
        messages = messages.filter {
            it.id != directMessageEntity.id
        }.toMutableList()
    }

    suspend fun save(directMessageEntity: DirectMessageEntity): String {
        if (directMessageEntity.id == null) {
            directMessageEntity.id = UUID.randomUUID().toString()
            messages.add(directMessageEntity)

            return directMessageEntity.id
        }

        return directMessageEntity.id
    }
}