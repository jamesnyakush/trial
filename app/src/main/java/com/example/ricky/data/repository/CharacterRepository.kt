package com.example.ricky.data.repository

import androidx.lifecycle.LiveData
import com.example.ricky.data.db.dao.CharacterDao
import com.example.ricky.data.db.model.Character
import com.example.ricky.data.network.RickyClient
import com.example.ricky.utils.BaseRepository
import javax.inject.Inject


class CharacterRepository @Inject constructor(
    private val rickyClient: RickyClient,
    private val characterDao: CharacterDao
) : BaseRepository() {

    suspend fun getCharacter() = safeApiCall {
        rickyClient.getCharacters()
    }

    fun getLocalCharacter(): LiveData<List<Character>> = characterDao.getCharacters()


    suspend fun saveToDb(characters: List<Character>) = characterDao.insertCharacters(characters)

}