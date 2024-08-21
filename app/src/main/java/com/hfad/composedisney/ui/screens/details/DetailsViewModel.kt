package com.hfad.composedisney.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.composedisney.ui.screens.details.domain.DetailsAction
import com.hfad.composedisney.ui.screens.details.domain.DetailsState
import com.hfad.composedisney.ui.screens.details.domain.HeroResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCase: LoadHeroByIdUseCase
) : ViewModel() {

    val state = MutableStateFlow(DetailsState())

    fun processAction(action: DetailsAction) {
        when (action) {
            is DetailsAction.Init -> loadHeroById(action.id)
        }
    }

    private fun loadHeroById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.loadHero(id).collect { result ->
                handleResult(result)
            }
        }
    }

    private fun handleResult(result: HeroResult) {
        when (result) {
            is HeroResult.Success -> state.tryEmit(
                state.value.copy(
                    hero = result.result,
                )
            )

            is HeroResult.Error -> {}
        }
    }
}