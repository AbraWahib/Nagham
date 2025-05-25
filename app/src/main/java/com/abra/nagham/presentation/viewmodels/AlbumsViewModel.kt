package com.abra.nagham.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abra.nagham.domain.model.music.Album
import com.abra.nagham.domain.usecases.data_retrieval.GetAllAlbumsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    private val getAllAlbumsUseCase: GetAllAlbumsUseCase
) : ViewModel() {
    private val _albums = MutableStateFlow<List<Album>>(emptyList())
    val albums: StateFlow<List<Album>> = _albums.asStateFlow()

    init {
        viewModelScope.launch {
            getAllAlbumsUseCase().collect { albums ->
                _albums.value = albums
            }
        }
    }
}