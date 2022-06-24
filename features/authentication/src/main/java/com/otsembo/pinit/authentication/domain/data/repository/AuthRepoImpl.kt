package com.otsembo.pinit.authentication.domain.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.otsembo.pinit.authentication.data.repository.AuthRepository
import kotlinx.coroutines.tasks.await

class AuthRepoImpl : AuthRepository {

    private val auth = FirebaseAuth.getInstance()

    override suspend fun createAccount(email: String, password: String, username: String): Boolean {
        var createSuccess = false
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                createSuccess = it.isSuccessful
            }.await()
        return createSuccess
    }

    override suspend fun login(email: String, password: String): Boolean {
        var loginSuccess = false
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                loginSuccess = it.isSuccessful
            }.await()
        return loginSuccess
    }

    companion object {
        private const val TAG = "AUTH-REPO"
    }
}
