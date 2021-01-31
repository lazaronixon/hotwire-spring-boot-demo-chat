package com.example.hotwirespringbootdemochat.models

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity @Table(name = "rooms")
class Room(
    var name: String? = null,
    override var createdAt: LocalDateTime? = null,
    override var updatedAt: LocalDateTime? = null,
    @Id @GeneratedValue override var id: Long? = null
) : ApplicationRecord<Long?>() {

    fun merge(other: Room) : Room {
        this.name = other.name; return this
    }

}