package com.mvince.compose.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(private val firebaseAuth: FirebaseAuth) {

    val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    suspend fun login(email: String, password: String): FirebaseUser? {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email.trim(), password).await()
            result.user
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun signup(email: String, password: String): FirebaseUser? {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email.trim(), password).await()
            result.user
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun logout() {
        firebaseAuth.signOut()
    }


}