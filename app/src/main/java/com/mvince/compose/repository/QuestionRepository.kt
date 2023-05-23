package com.mvince.compose.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.mvince.compose.domain.QuestionFirebase
import com.mvince.compose.network.QuestionOfTheDayApi
import com.mvince.compose.network.model.QuestionOfTheDayModelApi
import com.mvince.compose.network.model.Result
import kotlinx.coroutines.flow.first
import java.util.Date
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val api: QuestionOfTheDayApi,
    private val questionFirebaseRepository: QuestionFirebaseRepository
) {
    suspend fun getQuestions(): List<Result> {
        val response =  api.getQuestions()

        val questionOfTheDay = questionFirebaseRepository.getQuestionsOfTheDay()
        return if (questionOfTheDay.first().isEmpty()) {
            questionFirebaseRepository.insertQuestionsOfTheDay(QuestionFirebase(response.results, Date()))
            response.results
        }else{
            questionOfTheDay.first().first().questions
        }
    }
}