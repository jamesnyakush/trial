package com.example.ricky.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ricky.data.db.model.Character
import com.example.ricky.data.db.model.CharacterResponse
import com.example.ricky.data.repository.CharacterRepository
import com.example.ricky.utils.Resource
import kotlinx.coroutines.launch

class CharacterViewModel @ViewModelInject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _characterResponse: MutableLiveData<Resource<CharacterResponse>> = MutableLiveData()
    val characterResponse: LiveData<Resource<CharacterResponse>>
        get() = _characterResponse


    fun getCharacter() = viewModelScope.launch {
        _characterResponse.value = Resource.Loading
        _characterResponse.value = repository.getCharacter()

    }

    suspend fun saveToDb(characters: List<Character>) = repository.saveToDb(characters)

    fun getLocalCharacter() = repository.getLocalCharacter()

}