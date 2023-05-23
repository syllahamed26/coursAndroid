package com.mvince.compose.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import com.mvince.compose.domain.ScoreFirebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    fun getLastScoreByUser(userUid: String): Flow<List<ScoreFirebase>> {
        return firestore.collection(_collection)
            .whereEqualTo("user", userUid)
            .orderBy("date", Query.Direction.DESCENDING)
            .limit(1)
            .snapshots().map { it.toObjects<ScoreFirebase>() }
    }

    fun getAllScore(): Flow<List<ScoreFirebase>> {
        return firestore.collection(_collection).snapshots().map { it.toObjects<ScoreFirebase>() }
    }

    companion object {
        private const val _collection: String = "SCORES"
    }
}