package com.hfad.composedisney.ui.screens.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.composedisney.repository.DisneyRepository
import com.hfad.composedisney.ui.screens.details.domain.DetailsAction
import com.hfad.composedisney.ui.screens.details.domain.DetailsState
import com.example.compose_disney_characters.utils.toDisneyHero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: DisneyRepository
) : ViewModel() {

    val state = MutableLiveData(DetailsState())

    fun processAction(action: DetailsAction) {
        when (action) {
            is DetailsAction.Init -> loadHeroById(action.id)
        }
    }

    private fun loadHeroById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getHeroById(id)
            if (response.isSuccessful) {
                response.body()?.toDisneyHero().let {
                    state.postValue(it?.let { disneyHero -> state.value?.copy(hero = disneyHero) })
                }
            }
        }
    }
}