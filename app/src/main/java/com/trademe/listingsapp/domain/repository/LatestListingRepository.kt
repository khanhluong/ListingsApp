package com.trademe.listingsapp.domain.repository

import com.trademe.listingsapp.domain.model.LatestListing

interface LatestListingRepository {
    suspend fun getLatestListings(): Result<List<LatestListing>>
}