package com.example.hotwirespringbootdemochat.helpers

import com.example.hotwirespringbootdemochat.models.Room

object RoomsHelper {

    fun editRoomPath(room: Room) = "/rooms/edit?id=${room.id}"

    fun roomsPath() = "/rooms"

    fun newRoomPath() = "/rooms/new"

    fun roomPath(room: Room) = "/rooms/show?id=${room.id}"

}