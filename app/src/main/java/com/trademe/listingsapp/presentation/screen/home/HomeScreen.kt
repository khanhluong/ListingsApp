package com.trademe.listingsapp.presentation.screen.home

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import com.trademe.listingsapp.R
import com.trademe.listingsapp.presentation.navigation.Screen
import com.trademe.listingsapp.presentation.screen.mytrademe.MyTradeMeScreen
import com.trademe.listingsapp.presentation.screen.watchlist.WatchListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    val context = LocalContext.current
    var selectedTab by remember { mutableIntStateOf(value = 0) }
    val screens = listOf(Screen.LatestListings, Screen.Watchlist, Screen.MyTradeMe)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = screens[selectedTab].title,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                actions = {
                    IconButton(onClick = {
                        Toast.makeText(context, "Search tapped", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.search),
                            contentDescription = "Search"
                        )
                    }
                    IconButton(onClick = {
                        Toast.makeText(context, "Cart tapped", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.cart),
                            contentDescription = "Cart"
                        )
                    }
                }

            )
        },
        bottomBar = {
            NavigationBar {
                screens.forEachIndexed { index, screen ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                painterResource(screen.iconRes),
                                contentDescription = screen.title
                            )
                        },
                        label = { Text(screen.title) },
                        selected = selectedTab == index,
                        onClick = { selectedTab = index }
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (selectedTab) {
                0 -> LatestListingsContent()
                1 -> WatchListScreen()
                2 -> MyTradeMeScreen()
            }
        }
    }
}

@Composable
fun LatestListingsContent() {
    Text("LatestListings")
}