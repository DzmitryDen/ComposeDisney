package com.hfad.composedisney.ui.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.compose_disney_characters.ui.theme.Violet
import com.example.compose_disney_characters.ui.theme.VioletL
import com.hfad.composedisney.R
import com.hfad.composedisney.ui.screens.details.domain.DetailsAction

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailsScreen(
    navHostController: NavHostController,
    id: Int,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.observeAsState()
    viewModel.processAction(DetailsAction.Init(id))

    Scaffold(
        content = { padding ->
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .background(Violet)
                        .fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(290.dp)
                            .clip(shape = RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp))
                            .background(VioletL)
                    ) {
                        AsyncImage(
                            model = state.value?.hero?.image,
                            contentDescription = stringResource(id = R.string.image),
                            modifier = Modifier
                                .align(Alignment.Center)
                                .fillMaxSize()
                        )
                        IconButton(
                            onClick = { navHostController.popBackStack() },
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(20.dp, 40.dp, 0.dp, 0.dp)
                        ) {
                            Image(
                                imageVector = ImageVector.vectorResource(id = R.drawable.back_btn),
                                contentDescription = stringResource(id = R.string.back_button),
                            )
                        }
                    }
                    Text(
                        text = state.value?.hero?.name
                            ?: "",
                        fontSize = 30.sp,
                        color = Color.White,
                        modifier = Modifier
                            .padding(10.dp, 10.dp, 0.dp, 0.dp)

                    )
                    LazyColumn(contentPadding = PaddingValues(10.dp)) {
                        items(
                            items = state.value?.hero?.fields ?: arrayListOf()
                        ) { itemField ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                            ) {
                                Text(
                                    text = stringResource(id = itemField.nameRes),
                                    fontSize = 16.sp,
                                    color = Color.White,
                                    modifier = Modifier
                                        .padding(5.dp)
                                )
                                FlowRow(modifier = Modifier.padding(5.dp)) {
                                    itemField.list.forEach { itemsValue ->
                                        Box(
                                            modifier = Modifier
                                                .wrapContentSize()
                                                .padding(5.dp)
                                                .clip(shape = RoundedCornerShape(15.dp))
                                                .background(VioletL)
                                        ) {
                                            Text(
                                                text = itemsValue,
                                                fontSize = 14.sp,
                                                color = Color.White,
                                                modifier = Modifier.padding(
                                                    20.dp,
                                                    2.dp,
                                                    20.dp,
                                                    2.dp
                                                )
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}
