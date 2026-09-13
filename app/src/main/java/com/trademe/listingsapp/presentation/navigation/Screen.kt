package com.trademe.listingsapp.presentation.navigation

import androidx.annotation.DrawableRes
import com.trademe.listingsapp.R

sealed class Screen(
    val route: String,
    val title: String,
    @DrawableRes val iconRes: Int
) {
    object LatestListings : Screen(
        route = "latest",
        title = "Latest Listings",
        iconRes = R.drawable.search
    )

    object Watchlist: Screen(
        route = "watchlist",
        title = "Watchlist",
        iconRes = R.drawable.ic_binoculars
    )

    object MyTradeMe: Screen(
        route = "mytrademe",
        title = "My Trade Me",
        iconRes = R.drawable.profile
    )
}