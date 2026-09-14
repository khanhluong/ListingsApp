package com.trademe.listingsapp.data.repository

import com.trademe.listingsapp.data.remote.api.TradeMeApiService
import com.trademe.listingsapp.data.remote.dto.ListingDto
import com.trademe.listingsapp.domain.model.LatestListing
import com.trademe.listingsapp.domain.repository.LatestListingRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LatestListingRepositoryImpl @Inject constructor(
    private val apiService: TradeMeApiService
) : LatestListingRepository {

    companion object {
        private const val MAX_LISTINGS = 20
    }

    override suspend fun getLatestListings(): Result<List<LatestListing>> {
        return try {
            val response = apiService.getLatestListings()
            val listings = response.listings
                ?.take(MAX_LISTINGS)
                ?.map { it.toDomain() }
                ?: emptyList()

            Result.success(listings)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun ListingDto.toDomain(): LatestListing {
        return LatestListing(
            listingId = listingId ?: 0L,
            title = title ?: "",
            region = region ?: "",
            priceDisplay = priceDisplay ?: "",
            pictureUrl = pictureHref ?: photoUrls?.firstOrNull() ?: "",
            isClassified = isClassified ?: false,
            isAuction = isAuction ?: false,
            buyNowPrice = buyNowPrice
        )
    }
}