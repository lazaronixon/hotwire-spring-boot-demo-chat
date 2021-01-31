package com.example.hotwirespringbootdemochat.models

import javax.persistence.MappedSuperclass
import java.time.LocalDateTime
import java.util.*
import javax.persistence.PreUpdate
import javax.persistence.PrePersist
import javax.persistence.PostLoad
import javax.persistence.PostRemove
import javax.persistence.PostUpdate
import javax.persistence.PostPersist


@MappedSuperclass
abstract class ApplicationRecord<ID> {

    @Transient private var isNewRecord = true

    @Transient private var isDestroyed = false

    abstract var id: ID

    fun isNewRecord(): Boolean = isNewRecord

    fun isDestroyed(): Boolean = isDestroyed

    fun isPersisted(): Boolean = !(isNewRecord || isDestroyed)

    abstract var createdAt: LocalDateTime?

    abstract var updatedAt: LocalDateTime?

    @PrePersist
    private fun prePersist() {
        createdAt = LocalDateTime.now()
        updatedAt = LocalDateTime.now()
    }

    @PreUpdate
    private fun preUpdate() {
        updatedAt = LocalDateTime.now()
    }

    @PostPersist
    private fun postPersist() {
        isNewRecord = false
    }

    @PostUpdate
    private fun postUpdate() {
        isNewRecord = false
    }

    @PostRemove
    private fun postRemove() {
        isDestroyed = true
    }

    @PostLoad
    private fun postLoad() {
        isNewRecord = false
    }

    override fun hashCode(): Int {
        return Objects.hashCode(id)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        if (javaClass != other.javaClass) return false

        return Objects.equals(id, (other as ApplicationRecord<*>).id)
    }
}