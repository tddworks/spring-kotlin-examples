package com.tddworks.spring.kotlin.examples.coroutines.user.internal

import com.tddworks.spring.kotlin.examples.coroutines.user.api.User
import com.tddworks.spring.kotlin.examples.coroutines.user.api.Users
import com.tddworks.spring.kotlin.examples.coroutines.user.api.internal.RestUsersApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@WebMvcTest(RestUsersApi::class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = [RestUsersApi::class])
class RestUsersApiTest {
    @Autowired
    lateinit var mvc: MockMvc

    @MockitoBean
    lateinit var users: Users

    @Test
    fun `should return user when get by id`() = runTest {
        // given
        val user = User(username = "some-name", password = "some-password")

        whenever(users.findById(1)).thenReturn(user)

        // when
        val result = mvc.get("/users/{id}", 1)
        // then
        result
            .asyncDispatch()
            .andExpect {
                status { isOk() }
                content { json("""{"username": "${user.username}", "password": "${user.password}"}""") }
            }
    }

    @Test
    fun `should return success when create user`() = runTest {
        // given
        val user = User(username = "some-name", password = "some-password")

        whenever(users.save(user)).thenReturn(user)

        // when
        val request = """
                    {
                        "username": "${user.username}",
                        "password": "${user.password}"
                    }
                    """.trimIndent()
        val result = mvc.post("/users") {
            contentType = MediaType.APPLICATION_JSON
            content = request
        }

        // then
        result
            .asyncDispatch()
            .andExpect {
                status { isOk() }
                content { json("""{"username": "${user.username}", "password": "${user.password}"}""") }
            }
    }

}