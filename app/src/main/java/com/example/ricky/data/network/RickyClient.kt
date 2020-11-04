package com.example.ricky.data.network

import com.example.ricky.data.db.model.CharacterResponse
import retrofit2.http.GET

interface RickyClient{

    @GET("character")
    suspend fun getCharacters() : CharacterResponse
}