package com.tddworks.spring.kotlin.examples.coroutines.user.api

import kotlinx.coroutines.flow.Flow

interface UsersApi : FlowUsersApi {
    suspend fun createUser(user: User): User
    suspend fun getUser(id: Long): User?
    suspend fun updateUser(user: User): User
    suspend fun deleteUser(id: Long)
    suspend fun getUsers(): List<User>
}

interface FlowUsersApi {
    fun getFlowUsers(): Flow<User>
}