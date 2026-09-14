package com.trademe.listingsapp.domain.model

data class LatestListing(
    val listingId: Long,
    val title: String,
    val region: String,
    val priceDisplay: String,
    val pictureUrl: String,
    val isClassified: Boolean,
    val isAuction: Boolean,
    val buyNowPrice: Double?
)
