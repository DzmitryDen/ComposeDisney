package com.hfad.composedisney.ui.screens.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.compose_disney_characters.ui.screens.homeScreen.domain.HomeAction
import com.example.compose_disney_characters.ui.theme.Violet
import com.example.compose_disney_characters.ui.theme.VioletL
import com.hfad.composedisney.R
import com.hfad.composedisney.ui.navigation.ScreenRoute

private const val GREED = 2

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    viewModel.processAction(HomeAction.Init)

    Scaffold(
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(Violet)
            ) {
                LazyVerticalGrid(columns = GridCells.Fixed(GREED)) {
                    items(
                        items = state.value.disneyHeroes
                    ) { item ->
                        Column(
                            modifier = Modifier
                                .background(Violet)
                                .padding(10.dp)
                                .clickable {
                                    navHostController.navigate(ScreenRoute.Details.selectRoute(item.id))
                                }
                        ) {
                            AsyncImage(
                                model = item.imageUrl,
                                contentDescription = stringResource(id = R.string.image),
                                modifier = Modifier
                                    .height(150.dp)
                                    .width(170.dp)
                                    .background(VioletL),
                            )
                            Text(
                                text = item.name,
                                fontSize = 16.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(5.dp)
                            )
                        }
                    }
                }
            }
        }
    )
}