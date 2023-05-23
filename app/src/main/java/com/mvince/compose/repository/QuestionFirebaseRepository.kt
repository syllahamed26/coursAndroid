package com.mvince.compose.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import com.mvince.compose.domain.QuestionFirebase
import com.mvince.compose.network.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class QuestionFirebaseRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    suspend fun getQuestionsOfTheDay(): List<Result> {
        return firestore.collection(_collection).document(getDateOnKebabCase())
            .get()
            .await()
            .toObject(QuestionFirebase::class.java)?.questions ?: emptyList()
    }

    suspend fun questionsOfTheDayExist(): Boolean {
        return firestore.collection(_collection)
            .document(getDateOnKebabCase())
            .get()
            .await()
            .exists()
    }

    fun insertQuestionsOfTheDay(questions: QuestionFirebase): Boolean {
        return try {
            firestore.collection(_collection).document(getDateOnKebabCase()).set(questions).isSuccessful
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    private fun getDateOnKebabCase(): String {
        val date: Date = Date()
        val calendar = Calendar.getInstance()
        calendar.time = date
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        return "$day-$month-$year"
    }

    companion object {
        private const val _collection: String = "QUESTIONS"
    }
}