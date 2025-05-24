package com.abra.nagham.data.database

import androidx.room.TypeConverter

class TypeConverter{
    @TypeConverter
    fun fromSongIds(songIds: List<Long>): String {
        return songIds.joinToString(",")
    }

    @TypeConverter
    fun toSongIds(songIdsString: String): List<Long> {
        return if (songIdsString.isEmpty()) emptyList() else songIdsString.split(",").map { it.toLong() }
    }
}