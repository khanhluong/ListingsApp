package com.trademe.listingsapp.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TradeMeResponse(
    @SerialName("TotalCount")
    val totalCount: Int? = null,
    @SerialName("Page")
    val page: Int? = null,
    @SerialName("PageSize")
    val pageSize: Int? = null,
    @SerialName("List")
    val listings: List<ListingDto>? = null
)

@Serializable
data class ListingDto(
    @SerialName("__type")
    val type: String? = null,
    @SerialName("ListingId")
    val listingId: Long? = null,
    @SerialName("Title")
    val title: String? = null,
    @SerialName("Region")
    val region: String? = null,
    @SerialName("Suburb")
    val suburb: String? = null,
    @SerialName("PriceDisplay")
    val priceDisplay: String? = null,
    @SerialName("PictureHref")
    val pictureHref: String? = null,
    @SerialName("PhotoUrls")
    val photoUrls: List<String>? = null,
    @SerialName("IsClassified")
    val isClassified: Boolean? = null,
    @SerialName("IsAuction")
    val isAuction: Boolean? = null,
    @SerialName("BuyNowPrice")
    val buyNowPrice: Double? = null
)
