package com.abra.nagham.domain.model.playback

data class PlaybackState(
    val currentSongId: Long? = null,
    val songTitle: String? = null,
    val artistId: Long? = null,
    val artistName: String? = null,
    val albumId: Long? = null,
    val currentPosition: Long = 0L,
    val duration: Long = 0L,
    val isPlaying: Boolean = false,
    val isShuffleEnabled: Boolean = false
)
