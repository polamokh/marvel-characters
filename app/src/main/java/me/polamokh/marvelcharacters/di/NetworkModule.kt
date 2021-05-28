package me.polamokh.marvelcharacters.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.polamokh.marvelcharacters.network.MarvelService
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideMarvelService(): MarvelService {
        return MarvelService.create()
    }
}