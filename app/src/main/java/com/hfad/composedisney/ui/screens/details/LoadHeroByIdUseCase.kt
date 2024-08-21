package com.hfad.composedisney.ui.screens.details

import com.example.compose_disney_characters.utils.toDisneyHero
import com.hfad.composedisney.repository.DisneyRepository
import com.hfad.composedisney.ui.screens.details.domain.HeroResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoadHeroByIdUseCase @Inject constructor(private val reposit: DisneyRepository) {

    suspend fun loadHero(id: Int): Flow<HeroResult> {
        return flow {
            val response = reposit.getHeroById(id)

            if (response.isSuccessful) {
                emit(HeroResult.Success(response.body()?.toDisneyHero()))
            } else {
                emit(HeroResult.Error(Throwable(response.message())))
            }
        }.catch { e ->
            emit(HeroResult.Error(Throwable(e.message)))
        }
    }
}