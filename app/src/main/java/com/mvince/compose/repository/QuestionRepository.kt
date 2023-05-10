package com.mvince.compose.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.mvince.compose.network.QuestionOfTheDayApi
import com.mvince.compose.network.model.QuestionOfTheDayModelApi
import com.mvince.compose.network.model.Result
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val api: QuestionOfTheDayApi
) {
    suspend fun getQuestions(): List<Result> {
        val response =  api.getQuestions()
        return response.results
    }
}