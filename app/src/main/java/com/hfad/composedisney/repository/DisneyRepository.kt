package com.hfad.composedisney.repository

import com.hfad.composedisney.network.DisneyApi
import javax.inject.Inject

class DisneyRepository @Inject constructor(
    private val api: DisneyApi
) {

    suspend fun getListHero() = api.getCharacters()

    suspend fun getHeroById(id: Int) = api.getCharacterById(id)
}