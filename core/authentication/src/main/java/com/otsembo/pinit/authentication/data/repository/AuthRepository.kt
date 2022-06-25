package com.otsembo.pinit.authentication.data.repository

interface AuthRepository {

    suspend fun createAccount(email: String, password: String, username: String): Pair<Boolean, String>

    suspend fun login(email: String, password: String): Pair<Boolean, String>

    fun saveUserData(email: String, username: String)
}
