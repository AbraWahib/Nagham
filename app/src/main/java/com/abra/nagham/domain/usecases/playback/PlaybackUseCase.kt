package com.abra.nagham.domain.usecases.playback

import com.abra.nagham.domain.model.music.Song
import com.abra.nagham.domain.model.playback.PlaybackState
import com.abra.nagham.domain.repo.PlaybackRepository
import kotlinx.coroutines.flow.StateFlow

class PlaybackUseCase(
    private val playbackRepository: PlaybackRepository
) {
    val playbackState: StateFlow<PlaybackState>
        get() = playbackRepository.playbackState

    fun playSongs(songs: List<Song>, startIndex: Int) {
        playbackRepository.setMediaItems(
            mediaItems = songs.map {
                MediaItem(
                    id = it.songId,
                    filePath = it.filePath,
                    title = it.title
                )
            }
        )
        playbackRepository.seekTo(startIndex.toLong())
        playbackRepository.play()
    }

    fun play() {
        playbackRepository.play()
    }

    fun pause() {
        playbackRepository.pause()
    }

    fun seekTo(position: Long) {
        playbackRepository.seekTo(position)
    }

    fun setShuffleMode(enable: Boolean) {
        playbackRepository.setShuffleMode(enable)
    }

    data class MediaItem(
        val id: Long,
        val filePath: String,
        val title: String
    )

}
