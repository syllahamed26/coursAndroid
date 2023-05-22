package com.mvince.compose.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import com.mvince.compose.domain.ScoreFirebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject

class ScoreFirebaseRepository @Inject constructor(private val firestore: FirebaseFirestore) {

    fun insertScore(score: ScoreFirebase): Boolean {
        return try {
            firestore.collection(_collection).document().set(score).isSuccessful
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun getByUser(userUid: String): Flow<List<ScoreFirebase>> {
        val data = firestore.collection(_collection)
            .whereEqualTo("user", userUid)
            .snapshots().map { it.toObjects<ScoreFirebase>() }
        println(data)
        return data
    }

    companion object {
        private const val _collection: String = "SCORES"
    }
}