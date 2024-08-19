package com.hfad.composedisney.network

import com.hfad.composedisney.network.entity.DisneyHeroResponse
import com.hfad.composedisney.network.entity.ListDisneyHeroResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DisneyApi {

    @GET("character")
    suspend fun getCharacters() : Response<ListDisneyHeroResponse>

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path ("id") id: Int
    ): Response<DisneyHeroResponse>
}