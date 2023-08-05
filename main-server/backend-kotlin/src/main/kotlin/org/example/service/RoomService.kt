package org.example.service

import org.example.model.Room
import org.example.model.User
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.util.*

@Service
class RoomService(
    private val roomRepo: RoomRepo
) {
    fun getRooms(): List<Room> = roomRepo.findAll()

    fun getRoom(id: String): Room? = roomRepo.find(id)

}


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

        room.id?.let {
            var foundRoom = find(it)
            foundRoom = room

        }

        return room.id!!
    }

}