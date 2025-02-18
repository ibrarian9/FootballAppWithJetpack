package com.app.footballappwithjetpack.ui.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.app.footballappwithjetpack.ui.common.ClubBox
import com.app.footballappwithjetpack.ui.navigation.Screen
import com.app.footballappwithjetpack.ui.theme.MainColor
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues,
    navController: NavHostController,
    navigateToDetail: (Int) -> Unit,
    viewModel: HomeViewModel = getViewModel()
) {
    var isSearchVisible by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    val clubs by viewModel.clubs.observeAsState(emptyList())

    // filter
    val filteredClub = if (searchQuery.isEmpty()) {
        clubs
    } else {
        clubs.filter {
            it.name.contains(searchQuery, ignoreCase = true)
        }
    }

    // Main Container
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .background(MainColor)
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    modifier = modifier.weight(1f),
                    onClick = {
                        isSearchVisible = !isSearchVisible
                    }) {
                    Icon(
                        modifier = modifier.size(40.dp),
                        imageVector = Icons.Default.Search,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
                Spacer(modifier = modifier.weight(1f))
                Text(
                    text = "EPL NEWS APP",
                    fontSize = 15.sp,
                    color = Color.White,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = modifier.weight(1f))
                IconButton(
                    modifier = modifier.weight(1f),
                    onClick = {
                        navController.navigate(Screen.About.route)
                    }) {
                    Icon(
                        modifier = modifier.size(40.dp),
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }

            // Search Bar with Animation
            AnimatedVisibility(
                visible = isSearchVisible,
                enter = expandVertically(
                    expandFrom = Alignment.Top
                ) + fadeIn(
                    initialAlpha = 0.5f
                ),
                exit = slideOutVertically() + shrinkVertically() + fadeOut()
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Search") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(Icons.Default.Close, contentDescription = "Clear Search")
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    singleLine = true
                )
            }

            LazyColumn(
                modifier = modifier.fillMaxSize()
            ) {
                items(filteredClub) { club ->
                    ClubBox(
                        club = club,
                        navigateToDetail = {
                            navigateToDetail(club.id)
                        }
                    )
                }
            }
        }

        // FAB to navigate to Favorite screen
        FloatingActionButton(
            onClick = {
                navController.navigate(Screen.FavoriteClub.route)
            },
            containerColor = Color.White,
            contentColor = MainColor,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(30.dp)
        ) {
            Icon(Icons.Default.Favorite, contentDescription = "Favorite")
        }
    }

}