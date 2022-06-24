package com.otsembo.pinit.authentication.data.repository

interface AuthRepository {

    suspend fun createAccount(email: String, password: String, username: String): Boolean

    suspend fun login(email: String, password: String): Boolean
}
