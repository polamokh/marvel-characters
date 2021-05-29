package me.polamokh.marvelcharacters.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpotlightResource(
    val resourceURI: String,
    val name: String
) : Parcelable {
    @IgnoredOnParcel
    private val TAG = "Play"

    fun getId(): Int? {
        return try {
            val resourceUri = Uri.parse(resourceURI)
            resourceUri.lastPathSegment?.toInt()
        } catch (e: Exception) {
            null
        }
    }
}
