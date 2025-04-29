package org.example.dao.room

import org.example.api.room.Room
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class RoomRepo {

    var rooms: MutableList<Room> = ArrayList(1000)

    fun findAll(): List<Room> {
        return rooms.toList()
    }

    fun find(id: String): Room? {
        return rooms.find { it.id == id }
    }

    fun removeAll() {
        rooms = mutableListOf()
    }

    fun remove(room: Room) {
        rooms = rooms.filter {
            it.id != room.id
        }.toMutableList()
    }

    fun save(room: Room): String {

        if (room.id == null) {
            room.id = UUID.randomUUID().toString()
            rooms.add(room)

            return room.id!!
        }

        return room.id!!
    }

}