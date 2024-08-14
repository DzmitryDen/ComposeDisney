package com.hfad.composedisney.ui.screens.homeScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_disney_characters.ui.screens.homeScreen.domain.HomeAction
import com.example.compose_disney_characters.ui.screens.homeScreen.domain.HomeState
import com.hfad.composedisney.repository.DisneyRepository
import com.example.compose_disney_characters.utils.toDisneyHeroList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: DisneyRepository
) : ViewModel() {

    val state = MutableLiveData(HomeState())

    fun processAction(action: HomeAction) {
        when (action) {
            is HomeAction.Init -> loadListData()
        }
    }

    private fun loadListData() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getListHero()
            if (response.isSuccessful) {
                response.body()?.toDisneyHeroList().let {
                    state.postValue(it?.let { list ->
                        state.value?.copy(disneyHeroes = list) })
                }
            }
        }
    }
}