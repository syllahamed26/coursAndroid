package com.mvince.compose.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.mvince.compose.domain.UserFirebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserFirebaseRepository @Inject constructor(private val firestore: FirebaseFirestore) {

    fun insertUser(id: String, user: UserFirebase): Boolean {
        return try {
            firestore.collection(_collection).document(id).set(user)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun getAll(): Flow<List<UserFirebase>> {
        return firestore.collection(_collection).snapshots().map { it.toObjects<UserFirebase>() }
    }

    suspend fun getUserById(id: String): UserFirebase? {
        return firestore.collection(_collection)
            .document(id)
            .get().await().toObject(UserFirebase::class.java)
    }

    companion object {
        private const val _collection: String = "USERS"
    }
}