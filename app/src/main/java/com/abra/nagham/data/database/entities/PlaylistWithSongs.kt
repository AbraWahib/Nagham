package com.abra.nagham.data.database.entities

import androidx.room.Embedded

data class PlaylistWithSongs(
    @Embedded val playlist: PlayListEntity,
    val songIds: List<Long>
)