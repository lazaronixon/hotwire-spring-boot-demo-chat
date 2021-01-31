package com.example.hotwirespringbootdemochat.controllers

import com.example.hotwirespringbootdemochat.controllers.FlashType.*
import com.example.hotwirespringbootdemochat.exceptions.RecordNotFound
import com.example.hotwirespringbootdemochat.helpers.RoomsHelper.roomPath
import com.example.hotwirespringbootdemochat.helpers.RoomsHelper.roomsPath
import com.example.hotwirespringbootdemochat.models.Room
import com.example.hotwirespringbootdemochat.repositories.RoomRepository
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMethod.*
import org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller @Scope(SCOPE_REQUEST) @RequestMapping("rooms")
class RoomsController(private val repository: RoomRepository) : ApplicationController() {

    private lateinit var room : Room

    @GetMapping
    fun index(model: Model) {
        model["rooms"] = repository.findAll()
    }

    @GetMapping("show")
    fun show(@RequestParam id: Long, model: Model) {
        setRoom(id); model["room"] = room
    }

    @GetMapping("new")
    fun new(model: Model) {
        model["room"] = Room()
    }

    @GetMapping("edit")
    fun edit(@RequestParam id: Long, model: Model) {
        setRoom(id); model["room"] = room
    }

    @PostMapping
    fun create(newRoom: Room, flash: RedirectAttributes): String {
        room = repository.save(newRoom)
        return redirectTo(roomPath(room), flash, NOTICE, "Room was successfully created.")
    }

    @RequestMapping(method = [PATCH, PUT])
    fun update(@RequestParam id: Long, flash: RedirectAttributes, editedRoom: Room): String {
        setRoom(id)

        repository.save(room.merge(editedRoom))
        return redirectTo(roomPath(room), flash, NOTICE, "Room was successfully updated.")
    }

    @DeleteMapping
    fun destroy(@RequestParam id: Long, flash: RedirectAttributes): String {
        setRoom(id)

        repository.delete(room)
        return redirectTo(roomsPath(), flash, NOTICE, "Room was successfully destroyed.")
    }

    private fun setRoom(id: Long) {
        room = repository.findById(id).orElseThrow { RecordNotFound(Room::class, id) }
    }

}