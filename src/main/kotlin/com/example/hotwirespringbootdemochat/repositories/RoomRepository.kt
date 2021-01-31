package com.example.hotwirespringbootdemochat.repositories

import com.example.hotwirespringbootdemochat.models.Room
import org.springframework.data.jpa.repository.JpaRepository

interface RoomRepository : JpaRepository<Room, Long>