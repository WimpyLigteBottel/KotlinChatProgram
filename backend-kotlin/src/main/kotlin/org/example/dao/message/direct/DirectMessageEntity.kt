package org.example.dao.message.direct

import java.time.OffsetDateTime
import java.util.*

data class DirectMessageEntity(
    var id: String = UUID.randomUUID().toString(),
    var created: OffsetDateTime = OffsetDateTime.now(),
    var fromUserId: String,
    var toUserId: String,
    var message: String,
)
