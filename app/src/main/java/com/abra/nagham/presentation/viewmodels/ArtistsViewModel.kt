package com.abra.nagham.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abra.nagham.domain.model.music.Artist
import com.abra.nagham.domain.usecases.data_retrieval.GetAllArtistsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistsViewModel @Inject constructor(
    private val getAllArtistsUseCase: GetAllArtistsUseCase
) : ViewModel() {
    private val _artists = MutableStateFlow<List<Artist>>(emptyList())
    val artists: StateFlow<List<Artist>> = _artists.asStateFlow()
    init {
        viewModelScope.launch {
            getAllArtistsUseCase().collect {artists->
                _artists.value = artists
            }
        }
    }
}