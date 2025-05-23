package com.abra.nagham.domain.repo

import android.media.session.PlaybackState
import com.abra.nagham.domain.usecases.playback.PlaybackUseCase
import kotlinx.coroutines.flow.StateFlow

interface PlaybackRepository {
    fun setMediaItems(mediaItems: List<PlaybackUseCase.MediaItem>)
    fun play()
    fun pause()
    fun seekTo(position: Long)
    fun setShuffleMode(enable: Boolean)
    val playbackState: StateFlow<PlaybackState>
}