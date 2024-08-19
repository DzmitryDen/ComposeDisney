package com.example.compose_disney_characters.ui.screens.homeScreen.domain

import com.hfad.composedisney.models.DisneyHeroList

data class HomeState(
    val disneyHeroes: List<DisneyHeroList> = arrayListOf()
)
