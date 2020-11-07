package com.example.ricky.utils

import com.example.ricky.data.db.model.Character

interface CharacterItemClickListener {
    fun onItemClick(character: Character)
}