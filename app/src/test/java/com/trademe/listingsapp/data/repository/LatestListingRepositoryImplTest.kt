package com.trademe.listingsapp.data.repository

import com.trademe.listingsapp.data.remote.api.TradeMeApiService
import com.trademe.listingsapp.data.remote.dto.ListingDto
import com.trademe.listingsapp.data.remote.dto.TradeMeResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class LatestListingRepositoryImplTest {
    private lateinit var apiService: TradeMeApiService
    private lateinit var repository: LatestListingRepositoryImpl

    private val propertyType = "Property:http://api.trademe.co.nz/v1"

    @Before
    fun setup() {
        apiService = mockk()
        repository = LatestListingRepositoryImpl(apiService)
    }

    private fun listingDto(
        id: Long = 1L,
        type: String = propertyType,
        title: String = "Test",
        pictureHref: String? = "https://example.com/photo.jpg",
        photoUrls: List<String>? = null
    ) = ListingDto(
        type = type,
        listingId = id,
        title = title,
        region = "Auckland",
        suburb = "Ponsonby",
        priceDisplay = "$500k",
        pictureHref = pictureHref,
        photoUrls = photoUrls,
        isClassified = false
    )

    @Test
    fun `returns mapped listings on success`() = runTest {
        val dto = listingDto(id = 100, title = "Beautiful House")
        coEvery { apiService.getLatestListings() } returns TradeMeResponse(listings = listOf(dto))

        val result = repository.getLatestListings()

        assertTrue(result.isSuccess)
        val listing = result.getOrThrow().first()
        assertEquals(100L, listing.listingId)
        assertEquals("Beautiful House", listing.title)
        assertEquals("Auckland", listing.region)
    }

    @Test
    fun `limits to 20 listings`() = runTest {
        val listings = (1..30).map { listingDto(id = it.toLong()) }
        coEvery { apiService.getLatestListings() } returns TradeMeResponse(listings = listings)

        val result = repository.getLatestListings()

        assertTrue(result.isSuccess)
        assertEquals(20, result.getOrThrow().size)
    }

    @Test
    fun `handles null listings`() = runTest {
        coEvery { apiService.getLatestListings() } returns TradeMeResponse(listings = null)

        val result = repository.getLatestListings()

        assertTrue(result.isSuccess)
        assertEquals(0, result.getOrThrow().size)
    }

    @Test
    fun `returns failure on exception`() = runTest {
        coEvery { apiService.getLatestListings() } throws RuntimeException("API error")

        val result = repository.getLatestListings()

        assertTrue(result.isFailure)
        assertEquals("API error", result.exceptionOrNull()?.message)
    }

    @Test
    fun `maps pictureUrl from photoUrls when pictureHref is null`() = runTest {
        val dto = listingDto(pictureHref = null, photoUrls = listOf("https://fallback.com/img.jpg"))
        coEvery { apiService.getLatestListings() } returns TradeMeResponse(listings = listOf(dto))

        val result = repository.getLatestListings()

        assertEquals("https://fallback.com/img.jpg", result.getOrThrow().first().pictureUrl)
    }

    @Test
    fun `maps empty pictureUrl when both pictureHref and photoUrls are null`() = runTest {
        val dto = listingDto(pictureHref = null, photoUrls = null)
        coEvery { apiService.getLatestListings() } returns TradeMeResponse(listings = listOf(dto))

        val result = repository.getLatestListings()

        assertEquals("", result.getOrThrow().first().pictureUrl)
    }

}