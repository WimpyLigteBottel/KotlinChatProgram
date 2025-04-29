package org.example.dao.message.entity

import java.time.OffsetDateTime
import java.util.*

data class RoomMessageEntity(
    var id: String = UUID.randomUUID().toString(),
    var created: OffsetDateTime = OffsetDateTime.now(),
    var fromUserId: String,
    var toRoomId: String,
    var message: String,
)
