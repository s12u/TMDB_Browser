package com.tistory.mybstory.tmdbbrowser.data.repository

import com.tistory.mybstory.tmdbbrowser.data.remote.api.MediaType
import com.tistory.mybstory.tmdbbrowser.model.Title
import com.tistory.mybstory.tmdbbrowser.model.api.request.TrendingRequest
import com.tistory.mybstory.tmdbbrowser.model.api.response.TrendingResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getTrendingListByType(trendingRequest: TrendingRequest): TrendingResponse?
}
