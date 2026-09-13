package com.trademe.listingsapp.presentation.screen.watchlist

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.trademe.listingsapp.ui.theme.ListingsAppTheme


@Composable
fun WatchListScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Watchlist Coming soon")
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Composable
fun WatchListScreenReview() {
    ListingsAppTheme {
        WatchListScreen()
    }
}