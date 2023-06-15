package com.dgheorghe.userpost.domain

data class Post(
    val id: Long,
    val user_id: Long,
    val title: String,
    val body: String,
)
