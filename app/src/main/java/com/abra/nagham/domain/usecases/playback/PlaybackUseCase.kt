package com.abra.nagham.domain.usecases.playback

import android.media.session.PlaybackState
import com.abra.nagham.domain.models.music.Song
import com.abra.nagham.domain.repo.PlaybackRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class PlaybackUseCase @Inject constructor(
    private val playbackRepository: PlaybackRepository
) {
    val playbackState: StateFlow<PlaybackState>
        get() = playbackRepository.playbackState

    fun playSongs(songs: List<Song>, startIndex: Int) {
        playbackRepository.setMediaItems(
            mediaItems = songs.map {
                MediaItem(
                    id = it.id,
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
