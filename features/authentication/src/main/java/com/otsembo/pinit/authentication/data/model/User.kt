package com.otsembo.pinit.authentication.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val key: String,
    val username: String,
    val email: String
)
