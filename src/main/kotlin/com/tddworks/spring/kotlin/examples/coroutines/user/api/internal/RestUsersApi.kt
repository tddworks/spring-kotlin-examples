package com.tddworks.spring.kotlin.examples.coroutines.user.api.internal

import co.touchlab.kermit.Logger
import com.tddworks.spring.kotlin.examples.coroutines.user.api.User
import com.tddworks.spring.kotlin.examples.coroutines.user.api.Users
import com.tddworks.spring.kotlin.examples.coroutines.user.api.UsersApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class RestUsersApi(
    val users: Users
) : UsersApi {

    @PostMapping
    override suspend fun createUser(@RequestBody user: User): User {
        val save = users.save(user)
        Logger.i { "User created: $save" }
        return save
    }

    @GetMapping("/{id}")
    override suspend fun getUser(@PathVariable id: Long): User? {
        users.findById(id).let {
            return it
        }
    }

    @PutMapping
    override suspend fun updateUser(user: User): User {
        return users.save(user)
    }

    @DeleteMapping("/{id}")
    override suspend fun deleteUser(id: Long) {
        users.deleteById(id)
    }

    @GetMapping
    override suspend fun getUsers(): List<User> {
        return users.findAll().toList()
    }

    @GetMapping("/flow")
    override fun getFlowUsers(): Flow<User> {
        return users.findAll()
    }
}