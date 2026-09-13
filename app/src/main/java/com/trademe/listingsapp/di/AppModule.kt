package com.trademe.listingsapp.di

import com.trademe.listingsapp.data.remote.api.TradeMeApiService
import com.trademe.listingsapp.data.repository.LatestListingRepositoryImpl
import com.trademe.listingsapp.domain.repository.LatestListingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideLastestListingRepository(
        apiService: TradeMeApiService
    ): LatestListingRepository {
        return LatestListingRepositoryImpl(apiService)
    }
}