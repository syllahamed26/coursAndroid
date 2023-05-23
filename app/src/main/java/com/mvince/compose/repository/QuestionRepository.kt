package com.mvince.compose.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.mvince.compose.domain.QuestionFirebase
import com.mvince.compose.network.QuestionOfTheDayApi
import com.mvince.compose.network.model.QuestionOfTheDayModelApi
import com.mvince.compose.network.model.Result
import kotlinx.coroutines.flow.first
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val api: QuestionOfTheDayApi,
    private val questionFirebaseRepository: QuestionFirebaseRepository
) {
    suspend fun getQuestions(): List<Result> {
        val response = api.getQuestions()
        val questionOfTheDay = questionFirebaseRepository.getQuestionsOfTheDay()
        println("results ${response.results}")
        println("questionOfTheDay")
        return if (questionOfTheDay.first().isEmpty()) {
            val date: Date = Date()
            val calendar = Calendar.getInstance()
            calendar.time = date
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH) + 1
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val dateWithoutTime = "$day/$month/$year"
            println("dateWithoutTime $dateWithoutTime")
            questionFirebaseRepository.insertQuestionsOfTheDay(
                QuestionFirebase(
                    response.results,
                    dateWithoutTime
                )
            )
            response.results
        } else {
            questionOfTheDay.first()[0].questions
        }
    }
}