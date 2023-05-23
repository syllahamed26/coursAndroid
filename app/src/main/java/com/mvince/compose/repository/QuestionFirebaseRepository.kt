package com.mvince.compose.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import com.mvince.compose.domain.QuestionFirebase
import com.mvince.compose.domain.ScoreFirebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date
import javax.inject.Inject

class QuestionFirebaseRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    fun getQuestionsOfTheDay(): Flow<List<QuestionFirebase>> {
        return firestore.collection(_collection)
            .whereEqualTo("date", Date())
            .snapshots().map { it.toObjects<QuestionFirebase>() }
    }

    fun insertQuestionsOfTheDay(questions: QuestionFirebase): Boolean {
        return try {
            firestore.collection(_collection).document().set(questions).isSuccessful
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    companion object {
        private const val _collection: String = "QUESTIONS"
    }
}