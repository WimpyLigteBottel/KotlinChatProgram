package org.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class Launcher {
}


fun main(){
    runApplication<Launcher>()
}