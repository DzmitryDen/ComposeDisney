package com.example.compose_disney_characters.utils

import com.hfad.composedisney.R
import com.hfad.composedisney.models.DisneyHero
import com.hfad.composedisney.models.DisneyHeroList
import com.hfad.composedisney.models.Fields
import com.hfad.composedisney.network.entity.DisneyHeroResponse
import com.hfad.composedisney.network.entity.ListDisneyHeroResponse

fun ListDisneyHeroResponse.toDisneyHeroList(): List<DisneyHeroList> {
    val list = ArrayList<DisneyHeroList>()
    for (item in this.data) {
        list.add(
            DisneyHeroList(
                id = item._id,
                name = item.name,
                imageUrl = item.imageUrl
            )
        )
    }
    return list
}

fun DisneyHeroResponse.toDisneyHero(): DisneyHero {
    return this.data.run {
        val list = arrayListOf<Fields>()
        if (films.isNotEmpty()) {
            list.add(Fields(R.string.films, films))
        }
        if (parkAttractions.isNotEmpty()) {
            list.add(Fields(R.string.park_attractions, parkAttractions))
        }
        if (shortFilms.isNotEmpty()) {
            list.add(Fields(R.string.short_films, shortFilms))
        }
        if (tvShows.isNotEmpty()) {
            list.add(Fields(R.string.tv_shows, tvShows))
        }
        if (videoGames.isNotEmpty()) {
            list.add(Fields(R.string.video_games, videoGames))
        }
        DisneyHero(
            name = name,
            image = imageUrl,
            fields = list
        )
    }
}