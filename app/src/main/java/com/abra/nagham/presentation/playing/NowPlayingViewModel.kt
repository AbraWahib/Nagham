package com.abra.nagham.presentation.playing

import androidx.lifecycle.ViewModel
import com.abra.nagham.domain.model.playback.PlaybackState
import com.abra.nagham.domain.usecases.playback.PlaybackUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(
    private val playbackUseCase: PlaybackUseCase
) : ViewModel() {
    val playbackState: StateFlow<PlaybackState> = playbackUseCase.playbackState
    fun play() {
        playbackUseCase.play()
    }

    fun pause() {
        playbackUseCase.pause()
    }

    fun seekTo(position: Long) {
        playbackUseCase.seekTo(position)
    }

    fun toggleShuffle() {
        playbackUseCase.setShuffleMode(!playbackState.value.isShuffleEnabled)
    }
}