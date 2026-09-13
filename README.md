# ListingsApp

An Android application that displays the latest listings from the Trade Me sandbox API, built with Jetpack Compose and a clean architecture / MVVM structure.

## Features

- Fetches and displays the latest listings from the Trade Me Sandbox API
- Listing cards show the thumbnail, region, title and price
- Classified listings display an "Asking price" prefix with the price
- Bottom navigation with Latest Listings, Watchlist and My Trade Me tabs
- Loading, empty, and error states handled in the UI

## Tech Stack

- **UI**: Jetpack Compose (Material 3), Navigation Compose, Coil
- **Architecture**: MVVM with Clean Architecture patterns
- **DI**: Hilt + KSP
- **Networking**: Retrofit, OkHttp (with logging & auth interceptor), Kotlinx Serialization
- **Concurrency**: Kotlin Coroutines + Flow
- **Language**: Kotlin

## Project Structure

```
app/src/main/java/com/trademe/listingsapp/
├── ListingsApp.kt                  # Application class (@HiltAndroidApp)
├── MainActivity.kt                 # Single activity hosting the NavGraph
│
├── data/
│   ├── remote/
│   │   ├── api/
│   │   │   └── TradeMeApiService.kt    # Retrofit interface for the sandbox API
│   │   └── dto/
│   │       └── TradeMeResponse.kt      # DTOs for the API responses
│   └── repository/
│       └── LatestListingRepositoryImpl.kt  # Repository implementation
│
├── di/
│   ├── AppModule.kt                # Hilt module (repository bindings)
│   └── NetworkModule.kt            # Provides Json, OkHttpClient, Retrofit, TradeMeApiService
│
├── domain/
│   ├── model/
│   │   └── LatestListing.kt        # Domain model used by the UI
│   └── repository/
│       └── LatestListingRepository.kt   # Repository interface
│
└── presentation/
    ├── navigation/
    │   ├── NavGraph.kt             # NavHost definition and routes
    │   └── Screen.kt               # Screens definition (route, title, icon)
    ├── screen/
    │   ├── home/
    │   │   ├── HomeScreen.kt       # Home tab with tab navigation + listing list
    │   │   └── HomeViewModel.kt    # Home UI state + data loading
    │   ├── watchlist/
    │   │   └── WatchListScreen.kt  # Watchlist placeholder screen
    │   └── mytrademe/
    │       └── MyTradeMeScreen.kt  # My Trade Me placeholder screen
    └── ui/
        └── components/
            └── ListingCard.kt      # Reusable listing card composable
```

## Getting Started

1. Clone the repository.
2. Open the project in Android Studio.
3. Build and run the `app` module on an emulator/device.

> Note: The network module already includes a sandbox OAuth consumer key and signature for the Trade Me Sandbox API.
