package com.abra.nagham.di.modules

import android.content.Context
import androidx.room.Room
import com.abra.nagham.data.database.NaghamDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context:Context): NaghamDatabase{
        return Room.databaseBuilder(
            context,
            NaghamDatabase::class.java,
            "nagham.db"
        ).build()
    }
    @Provides
    @Singleton
    fun providePlaylistDao(database: NaghamDatabase) =
        database.playlistDao()
}