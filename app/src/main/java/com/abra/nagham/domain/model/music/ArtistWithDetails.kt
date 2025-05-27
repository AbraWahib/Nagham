package com.abra.nagham.domain.model.music

data class ArtistWithDetails(
    val artist: Artist,
    val songCount:Int,
    val imageUri: String?
)