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
    fun onEvent(event: NowPlayingEvent){
        when(event){
            is NowPlayingEvent.Play -> playbackUseCase.play()
            NowPlayingEvent.Next -> playbackUseCase.next()
            NowPlayingEvent.Pause -> playbackUseCase.pause()
            NowPlayingEvent.Previous -> playbackUseCase.previous()
            is NowPlayingEvent.SeekTo -> playbackUseCase.seekTo(event.position)
            NowPlayingEvent.ToggleShuffle -> playbackUseCase.setShuffleMode(!playbackState.value.isShuffleEnabled)
        }
    }
}