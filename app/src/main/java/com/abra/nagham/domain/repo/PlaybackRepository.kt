package com.abra.nagham.domain.repo

import com.abra.nagham.domain.model.playback.PlaybackState
import com.abra.nagham.domain.usecases.playback.PlaybackUseCase
import kotlinx.coroutines.flow.StateFlow

interface PlaybackRepository {
    fun setMediaItems(mediaItems: List<PlaybackUseCase.MediaItem>)
    fun play()
    fun pause()
    fun next()
    fun previous()
    fun seekTo(position: Long)
    fun seekToIndex(index: Int)
    fun setShuffleMode(enable: Boolean)
    val playbackState: StateFlow<PlaybackState>
}