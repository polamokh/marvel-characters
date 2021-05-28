package me.polamokh.marvelcharacters.model

data class MarvelCharacter(
    val name: String,
    val description: String,
    val thumbnail: CharacterImage,
    val comics: Spotlight,
    val stories: Spotlight,
    val events: Spotlight,
    val series: Spotlight,
    val id: Int,
)
