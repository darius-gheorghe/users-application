package com.dgheorghe.userpost.repository

import com.dgheorghe.userpost.domain.Post
import com.dgheorghe.userpost.domain.User

object DummyData {
    val userList: List<User> = listOf(
        User(
            id = 0,
            name = "Darius Gheorghe",
            email = "darghe98@gmail.com",
            status = "active"
        ),
        User(
            id = 1,
            name = "Marius Gheorghe",
            email = "email0@gmail.com",
            status = "inactive"
        ),
        User(
            id = 2,
            name = "Flavius Gheorghe",
            email = "email1@gmail.com",
            status = "inactive"
        ),
        User(
            id = 3,
            name = "Marian Anton",
            email = "email2@gmail.com",
            status = "active"
        ),
        User(
            id = 4,
            name = "Diana Ion",
            email = "email3@gmail.com",
            status = "active"
        ),
        User(
            id = 5,
            name = "Elisabeta Atodiresei",
            email = "email4@gmail.com",
            status = "active"
        ),
        User(
            id = 7,
            name = "Bogdan Berescu",
            email = "email5@gmail.com",
            status = "active"
        ),
        User(
            id = 8,
            name = "Nicoleta Onofrei",
            email = "email6@gmail.com",
            status = "active"
        ),
        User(
            id = 10,
            name = "Nicoleta Onofrei",
            email = "email6@gmail.com",
            status = "active"
        ),
        User(
            id = 12,
            name = "Nicoleta Onofrei",
            email = "email6@gmail.com",
            status = "active"
        ),
        User(
            id = 13,
            name = "Nicoleta Onofrei",
            email = "email6@gmail.com",
            status = "active"
        ),
        User(
            id = 14,
            name = "Nicoleta Onofrei",
            email = "email6@gmail.com",
            status = "active"
        )
    )

    private val mockedUserPost = Post(
        id = 15,
        user_id = 0,
        title = "Inflatia in zilele noastre...",
        body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
    )

    fun getDummyPostList(userId: Long) = listOf(
        mockedUserPost.copy(user_id = userId)
    )
}