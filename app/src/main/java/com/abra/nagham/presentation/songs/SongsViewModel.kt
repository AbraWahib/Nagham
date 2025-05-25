package com.abra.nagham.presentation.songs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abra.nagham.domain.model.music.Song
import com.abra.nagham.domain.usecases.data_retrieval.GetAllSongsUseCase
import com.abra.nagham.domain.usecases.playback.PlaybackUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongsViewModel @Inject constructor(
    private val getAllSongsUseCase: GetAllSongsUseCase,
    private val playbackUseCase: PlaybackUseCase
) : ViewModel() {
    private val _songs = MutableStateFlow<List<Song>>(emptyList())
    val songs: StateFlow<List<Song>> = _songs.asStateFlow()

    init {
        viewModelScope.launch {
            getAllSongsUseCase().collect {
                _songs.value = it
            }
        }
    }

    fun playSong(song: Song) {
        viewModelScope.launch {
            _songs.value.let { songs ->
                val index = songs.indexOf(song)
                if (index >= 0) {
                    playbackUseCase.playSongs(songs, index)
                }
            }
        }
    }
}