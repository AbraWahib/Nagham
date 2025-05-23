package com.abra.nagham.domain.model.music

data class Song(
    val songId: Long,
    val title: String,
    val artistId: Long,
    val albumId: Long,
    val duration: Long,
    val filePath: String
)
