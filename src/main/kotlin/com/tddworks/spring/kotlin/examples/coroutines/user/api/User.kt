package com.tddworks.spring.kotlin.examples.coroutines.user.api

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class User(
    @Id
    val id: Long = 0,
    var username: String,
    var password: String
)