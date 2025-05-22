package com.abra.nagham.domain.repo

import android.media.browse.MediaBrowser
import android.media.session.PlaybackState
import kotlinx.coroutines.flow.Flow

interface PlaybackRepository {
    fun setMediaItems(mediaItems: List<MediaBrowser.MediaItem>)
    fun play()
    fun pause()
    fun seekTo(position: Long)
    fun setShuffleMode(enable: Boolean)
    val playbackState: Flow<PlaybackState>
}