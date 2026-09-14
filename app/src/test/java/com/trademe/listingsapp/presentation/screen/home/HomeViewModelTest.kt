package com.trademe.listingsapp.presentation.screen.home

import com.trademe.listingsapp.domain.model.LatestListing
import com.trademe.listingsapp.domain.repository.LatestListingRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: LatestListingRepository


    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun createViewModel() = HomeViewModel(repository)

    private fun sampleListings() = listOf(
        LatestListing(
            1, "House", "Auckland", "$500k", "url1",
            isClassified = false,
            isAuction = false,
            buyNowPrice = null
        ),
        LatestListing(
            2,
            "Unit",
            "Wellington",
            "$400k",
            "url2",
            isClassified = true,
            isAuction = false,
            buyNowPrice = null
        )
    )

    @Test
    fun `initial state emits listings on success`() = runTest {
        coEvery { repository.getLatestListings() } returns Result.success(sampleListings())
        val viewModel = createViewModel()
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertEquals(2, state.listings.size)
        assertNull(state.error)
    }

    @Test
    fun `emits error on failure`() = runTest {
        coEvery { repository.getLatestListings() } returns Result.failure(Exception("Network error"))
        val viewModel = createViewModel()
        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertEquals("Network error", state.error)
        assertTrue(state.listings.isEmpty())
    }

    @Test
    fun `clearError clears error state`() = runTest {
        coEvery { repository.getLatestListings() } returns Result.failure(Exception("Error"))
        val viewModel = createViewModel()
        advanceUntilIdle()

        viewModel.clearError()

        assertNull(viewModel.uiState.value.error)
    }

}