package com.hfad.composedisney.network.entity

data class Data(
    val _id: Int,
    val films: List<String>,
    val imageUrl: String,
    val name: String,
    val parkAttractions: List<String>,
    val shortFilms: List<String>,
    val tvShows: List<String>,
    val videoGames: List<String>
)