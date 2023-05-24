package com.mvince.compose.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.mvince.compose.domain.QuestionFirebase
import com.mvince.compose.network.QuestionOfTheDayApi
import com.mvince.compose.network.model.Result
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

        val questionOfTheDayExist = questionFirebaseRepository.questionsOfTheDayExist()
        return if (!questionOfTheDayExist) {
            questionFirebaseRepository.insertQuestionsOfTheDay(
                QuestionFirebase(
                    response.results,
                )
            )
            response.results
        } else {
            questionFirebaseRepository.getQuestionsOfTheDay()
        }
    }
}