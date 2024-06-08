package com.example.takenotes.di

import android.content.Context
import androidx.room.Room
import com.example.takenotes.data.NoteDB
import com.example.takenotes.data.NoteDBDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNotesOn(noteDB: NoteDB): NoteDBDao = noteDB.noteDao()


    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): NoteDB
        = Room.databaseBuilder(
            context,
            NoteDB::class.java,
            "notes"
        )
        .fallbackToDestructiveMigration()
        .build()

}