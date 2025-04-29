package org.example.api.message

import org.example.api.message.model.MessageDeleteRequest
import org.example.api.message.model.MessageRequest
import org.example.api.message.model.MessageUpdateRequest
import org.example.service.message.MessageService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1")
@CrossOrigin
class MessageController(
    private val messageService: MessageService
) {

    @PostMapping("/message")
    suspend fun sendMessage(@RequestBody messageRequest: MessageRequest): String {
        messageService.sendMessage(messageRequest)

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