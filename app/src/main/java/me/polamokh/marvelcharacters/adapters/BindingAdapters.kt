package me.polamokh.marvelcharacters.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("setImage")
fun ImageView.bindCharacterImage(imageUrl: String?) {
    imageUrl?.let {
        Glide.with(this.context)
            .load(it)
            .centerCrop()
            .into(this)
    }
}