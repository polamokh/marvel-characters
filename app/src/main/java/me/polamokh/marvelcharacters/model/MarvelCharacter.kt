package me.polamokh.marvelcharacters.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarvelCharacter(
    val name: String,
    val description: String,
    val thumbnail: Thumbnail,
    val id: Int,
) : Parcelable
