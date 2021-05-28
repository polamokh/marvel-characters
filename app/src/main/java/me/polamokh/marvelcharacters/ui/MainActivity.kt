package me.polamokh.marvelcharacters.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import me.polamokh.marvelcharacters.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MarvelCharacters)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}