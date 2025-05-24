package com.abra.nagham.domain.model.playback

data class PlaybackState(
    val currentSongId: Long? = null,
    val isPlaying: Boolean = false,
    val currentPosition: Long = 0L,
    val duration: Long = 0L,
    val isShuffleEnabled: Boolean = false
)
