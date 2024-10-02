package com.tddworks.spring.kotlin.examples.coroutines.user.api

import co.touchlab.kermit.Logger
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.domain.AbstractAggregateRoot
import org.springframework.data.domain.AfterDomainEventPublication

@Entity
data class User(
    @Id
    val id: Long = 0,
    var username: String,
    var password: String
) : AbstractAggregateRoot<User>() {

    fun register(): User {
        registerEvent(UserCreatedEvent(this))
        return this
    }

    @AfterDomainEventPublication
    fun callback() {
        Logger.i { "User registered: $this" }
    }
}

data class UserCreatedEvent(val user: User)