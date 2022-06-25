package com.otsembo.pinit.authentication.domain.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.otsembo.pinit.authentication.data.repository.AuthRepository
import kotlinx.coroutines.tasks.await

class AuthRepoImpl : AuthRepository {

    private val auth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore

    override suspend fun createAccount(email: String, password: String, username: String): Pair<Boolean, String> {
        var createSuccess = false
        var message = "success"
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                createSuccess = it.isSuccessful
                if (createSuccess) saveUserData(email, username) else message = it.exception?.message ?: "error"
            }.await()

        return Pair(createSuccess, message)
    }

    override suspend fun login(email: String, password: String): Pair<Boolean, String> {
        var loginSuccess = false
        var message = "success"
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    loginSuccess = it.isSuccessful
                }.await()
        } catch (e: Exception) {
            message = e.message ?: e.toString()
        }

        return Pair(loginSuccess, message)
    }

    override fun saveUserData(email: String, username: String) {
        db.collection(USER_DB).document()
            .set(hashMapOf("email" to email, "username" to username))
    }

    companion object {
        private const val TAG = "AUTH-REPO"
        private const val USER_DB = "users"
    }
}
