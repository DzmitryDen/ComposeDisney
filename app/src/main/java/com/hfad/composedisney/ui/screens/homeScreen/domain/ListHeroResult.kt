package com.hfad.composedisney.ui.screens.homeScreen.domain

import com.hfad.composedisney.models.DisneyHeroList

sealed class ListHeroResult {

    data class Success(val heroes: List<DisneyHeroList>) : ListHeroResult()

    data class Error(val throwable: Throwable) : ListHeroResult()
}