package com.abra.nagham.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abra.nagham.data.data_source.PlaylistDao
import com.abra.nagham.data.database.entities.PlayListEntity
import com.abra.nagham.data.database.entities.PlaylistSongEntity

@Database(entities = [PlayListEntity::class, PlaylistSongEntity::class], version = 1)
abstract class NaghamDatabase : RoomDatabase() {
    abstract fun playlistDao(): PlaylistDao
}