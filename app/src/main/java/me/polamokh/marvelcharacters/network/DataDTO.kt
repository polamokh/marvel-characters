package me.polamokh.marvelcharacters.network

import me.polamokh.marvelcharacters.model.MarvelCharacter

data class DataDTO(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<MarvelCharacter>
)
