package com.mvince.compose.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import com.mvince.compose.domain.QuestionFirebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class QuestionFirebaseRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    fun getQuestionsOfTheDay(): Flow<List<QuestionFirebase>> {
        val date: Date = Date()
        val calendar = Calendar.getInstance()
        calendar.time = date
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val dateWithoutTime = "$day/$month/$year"
        return firestore.collection(_collection)
            .whereEqualTo("date", dateWithoutTime)
            .limit(1)
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