package com.trademe.listingsapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object LatestListings : Screen(
        route = "latest",
        title = "Latest Listings",
        icon = Icons.AutoMirrored.Filled.List
    )

    object Watchlist: Screen(
        route = "watchlist",
        title = "Watchlist",
        icon = Icons.Default.FavoriteBorder
    )

    object MyTradeMe: Screen(
        route = "mytrademe",
        title = "My Trade Me",
        icon = Icons.Default.Person
    )
}