package me.polamokh.marvelcharacters.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Spotlight(
    val items: List<SpotlightResource>
) : Parcelable
