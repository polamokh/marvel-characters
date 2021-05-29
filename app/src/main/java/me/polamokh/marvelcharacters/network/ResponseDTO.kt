package me.polamokh.marvelcharacters.network

data class ResponseDTO<T>(
    val data: DataDTO<T>
)
