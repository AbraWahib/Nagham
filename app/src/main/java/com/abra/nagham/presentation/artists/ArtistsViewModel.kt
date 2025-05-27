package com.abra.nagham.presentation.artists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abra.nagham.domain.model.music.Artist
import com.abra.nagham.domain.model.music.ArtistWithDetails
import com.abra.nagham.domain.usecases.data_retrieval.GetAllArtistsUseCase
import com.abra.nagham.domain.usecases.data_retrieval.GetAllSongsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistsViewModel @Inject constructor(
    private val getAllArtistsUseCase: GetAllArtistsUseCase,
    private val getAllSongsUseCase: GetAllSongsUseCase
) : ViewModel() {
    private val _artistsWithDetails = MutableStateFlow<List<ArtistWithDetails>>(emptyList())
    val artistsWithDetails: StateFlow<List<ArtistWithDetails>> = _artistsWithDetails.asStateFlow()
    init {
        viewModelScope.launch {
            combine(
                getAllArtistsUseCase(),
                getAllSongsUseCase()
            ){artists,songs->
                artists.map {artist->
                    val artistSongs = songs.filter { it.artistId == artist.id }
                    val imageUri = artistSongs.firstOrNull()?.let {
                        getAlbumArtUri(it.albumId) }
                    ArtistWithDetails(
                        artist,
                        artistSongs.size,
                        imageUri
                    )
                }
            }.collect{
                _artistsWithDetails.value = it
            }
        }
    }
    private fun getAlbumArtUri(albumId: Long): String {
        return "content://media/external/audio/albumart/$albumId"
    }
}