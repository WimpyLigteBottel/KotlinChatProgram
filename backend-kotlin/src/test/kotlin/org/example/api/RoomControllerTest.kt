package org.example.api

import org.assertj.core.api.Assertions.assertThat
import org.example.model.Room
import org.example.model.User
import org.example.service.RoomRepo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RoomControllerTest {

    @Autowired
    lateinit var roomController: RoomController


    @Autowired
    lateinit var roomRepo: RoomRepo


    @BeforeEach
    fun before() {
        roomRepo.removeAll()
    }

    @Test
    fun `get room by id`() {
        val roomId = roomRepo.save(Room(name = "dude", owner = User(id = "1", name = "ben")))

        assertThat(roomController.getRoom(roomId)).isNotNull
    }


    @Test
    fun `get all rooms`() {
        roomRepo.save(Room(name = "dude", owner = User(id = "1", name = "ben")))
        roomRepo.save(Room(name = "dude", owner = User(id = "1", name = "ben")))

        assertThat(roomController.getRoomList()).isNotNull
        assertThat(roomController.getRoomList()).hasSize(2)
    }


}