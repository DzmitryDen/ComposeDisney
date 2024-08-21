package com.hfad.composedisney.ui.screens.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_disney_characters.ui.screens.homeScreen.domain.HomeAction
import com.example.compose_disney_characters.ui.screens.homeScreen.domain.HomeState
import com.hfad.composedisney.ui.screens.homeScreen.domain.ListHeroResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: LoadListUseCase
) : ViewModel() {

    val state = MutableStateFlow(HomeState())

    fun processAction(action: HomeAction) {
        when (action) {
            is HomeAction.Init -> loadListData()
        }
    }

    private fun loadListData() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.loadListHero().collect { result ->
                handleResult(result)
            }
        }
    }

    private fun handleResult(result: ListHeroResult) {
        when (result) {
            is ListHeroResult.Success -> state.tryEmit(
                state.value.copy(
                    disneyHeroes = result.heroes,
                )
            )

            is ListHeroResult.Error -> {}
        }
    }
}