package com.tddworks.spring.kotlin.examples.coroutines.user.api

import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface Users : CoroutineCrudRepository<User, Long>