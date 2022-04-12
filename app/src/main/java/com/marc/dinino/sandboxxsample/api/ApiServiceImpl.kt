package com.marc.dinino.sandboxxsample.api

import com.marc.dinino.sandboxxsample.model.mostpopular.Response
import com.marc.dinino.sandboxxsample.model.mostpopular.Result
import io.ktor.client.HttpClient
import io.ktor.client.features.*
import io.ktor.client.request.*

class ApiServiceImpl(private val client: HttpClient): ApiService {

    override suspend fun getMostPopularOfLastDay(): List<Result> {
        return try{
            val response: Response = client.get { url(ApiRoutes.MOST_VIEWED_LAST_DAY) }
            response.results
        } catch (e: Exception) {
            emptyList()
        }

    }
}