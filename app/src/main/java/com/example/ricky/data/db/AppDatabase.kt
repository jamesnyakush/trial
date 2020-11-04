package com.example.ricky.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ricky.data.db.dao.CharacterDao
import com.example.ricky.data.db.model.Character

@Database(
    entities = [Character::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun characterDao() : CharacterDao
}