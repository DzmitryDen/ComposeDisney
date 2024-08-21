package com.hfad.composedisney.ui.screens.homeScreen

import com.example.compose_disney_characters.utils.toDisneyHeroList
import com.hfad.composedisney.repository.DisneyRepository
import com.hfad.composedisney.ui.screens.homeScreen.domain.ListHeroResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoadListUseCase @Inject constructor(private val reposit: DisneyRepository) {

    suspend fun loadListHero(): Flow<ListHeroResult> {
        return flow {
            val response = reposit.getListHero()
            if (response.isSuccessful) {
                emit(ListHeroResult.Success(response.body()?.toDisneyHeroList() ?: arrayListOf()))
            } else {
                emit(ListHeroResult.Error(Throwable(response.message())))
            }
        }.catch { e ->
            emit(ListHeroResult.Error(Throwable(e.message)))
        }
    }
}