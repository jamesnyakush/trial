package com.example.ricky.di

import android.content.Context
import androidx.room.Room
import com.example.ricky.data.db.AppDatabase
import com.example.ricky.data.db.dao.CharacterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "ricky_db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun providesCharacterDao(appDatabase: AppDatabase): CharacterDao =
        appDatabase.characterDao()


}