package org.example.api.message.room

import org.example.api.message.model.MessageDeleteRequest
import org.example.api.message.model.MessageUpdateRequest
import org.example.service.message.RoomMessageService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/room")
@CrossOrigin
class RoomMessageController(
    private val messageService: RoomMessageService
) {

    @PostMapping("/message")
    suspend fun sendMessage(@RequestBody messageRoomRequest: MessageRoomRequest): String {
        messageService.sendMessage(messageRoomRequest)

        return "success"
    }

    @PutMapping("/message")
    suspend fun updateMessage(@RequestBody messageUpdateRequest: MessageUpdateRequest): String {
        messageService.updateMessage(messageUpdateRequest)

        return "success"
    }

    @DeleteMapping("/message")
    suspend fun deleteMessage(@PathVariable deleteRequest: MessageDeleteRequest): String {
        messageService.deleteMessage(deleteRequest)

        return "success"
    }
}