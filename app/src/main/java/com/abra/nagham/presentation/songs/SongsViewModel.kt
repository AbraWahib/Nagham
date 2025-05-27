package com.abra.nagham.presentation.songs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abra.nagham.domain.model.music.Song
import com.abra.nagham.domain.model.music.SongWithArtist
import com.abra.nagham.domain.usecases.data_retrieval.GetAllArtistsUseCase
import com.abra.nagham.domain.usecases.data_retrieval.GetAllSongsUseCase
import com.abra.nagham.domain.usecases.playback.PlaybackUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongsViewModel @Inject constructor(
    private val getAllSongsUseCase: GetAllSongsUseCase,
    private val getAllArtistsUseCase: GetAllArtistsUseCase,
    private val playbackUseCase: PlaybackUseCase
) : ViewModel() {
    private val _songsWithArtist = MutableStateFlow<List<SongWithArtist>>(emptyList())
    val songsWithArtist: StateFlow<List<SongWithArtist>> = _songsWithArtist.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                getAllSongsUseCase(),
                getAllArtistsUseCase()
            ) { songs, artists ->
                val artistMap = artists.associate { it.id to it.name }
                songs.map {
                    SongWithArtist(
                        song = it,
                        artistName = artistMap[it.artistId] ?: "Unknown Artist"
                    )
                }
            }.collect {
                _songsWithArtist.value = it
            }
        }
    }

    fun playSong(song: Song) {
        viewModelScope.launch {
            _songsWithArtist.value.let { songs ->
                val index = songs.indexOfFirst { it.song.songId == song.songId }
                if (index >= 0) {
                    playbackUseCase.playSongs(songs.map { it.song }, index)
                }
            }
        }
    }
}