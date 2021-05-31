package me.polamokh.marvelcharacters.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Thumbnail(
    val path: String,
    val extension: String
) : Parcelable {

    /**
     * Get full url of thumbnail.
     *
     * @return String url.
     */
    fun getImageUrl() = "$path.$extension"
}
