package org.example.advise

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class AdviseController: ResponseEntityExceptionHandler() {


    val logger: Logger =  LoggerFactory.getLogger(this::class.java)


    @ExceptionHandler(value = [RuntimeException::class])
    fun handleException(e: RuntimeException): ResponseEntity<String?> {
        logger.error(e)
        return ResponseEntity.internalServerError().body(e.message)
    }
}