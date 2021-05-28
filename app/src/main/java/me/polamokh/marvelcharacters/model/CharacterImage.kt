package me.polamokh.marvelcharacters.model

data class CharacterImage(
    val path: String,
    val extension: String
) {
    fun getImageUrl() = "$path.$extension"
}
