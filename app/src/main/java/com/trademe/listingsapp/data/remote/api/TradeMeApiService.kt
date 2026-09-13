package com.trademe.listingsapp.data.remote.api

import com.trademe.listingsapp.data.remote.dto.TradeMeResponse
import retrofit2.http.GET

interface TradeMeApiService {
    @GET("v1/listings/latest.json")
    suspend fun getLatestListings(): TradeMeResponse
}