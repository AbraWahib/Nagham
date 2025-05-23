package com.abra.nagham.domain.models.music

data class Song(
    val id: Long,
    val title: String,
    val artistId: Long,
    val albumId: Long,
    val duration: Long,
    val filePath: String
)
