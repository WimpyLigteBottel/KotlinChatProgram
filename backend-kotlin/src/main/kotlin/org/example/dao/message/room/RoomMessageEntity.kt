package org.example.dao.message.room

import java.time.OffsetDateTime
import java.util.*

data class RoomMessageEntity(
    var id: String? = null,
    var created: OffsetDateTime = OffsetDateTime.now(),
    var fromUserId: String,
    var toRoomId: String,
    var message: String,
)
