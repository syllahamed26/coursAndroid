package com.mvince.compose.network

import com.mvince.compose.network.model.QuestionOfTheDayModelApi
import retrofit2.http.GET
import retrofit2.http.Query

interface QuestionOfTheDayApi {
    @GET("api.php")
    suspend fun getQuestions(@Query("amount") amount: Int = 10): QuestionOfTheDayModelApi
}