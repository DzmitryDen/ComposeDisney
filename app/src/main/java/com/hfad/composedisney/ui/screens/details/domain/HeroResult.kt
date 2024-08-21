package com.hfad.composedisney.ui.screens.details.domain

import com.hfad.composedisney.models.DisneyHero

sealed class HeroResult {
    data class Success(val result: DisneyHero?) : HeroResult()
    data class Error(val throwable: Throwable) : HeroResult()
}