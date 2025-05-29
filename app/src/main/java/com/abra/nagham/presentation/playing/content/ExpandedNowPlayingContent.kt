package com.abra.nagham.presentation.playing.content

import android.view.RoundedCorner
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.abra.nagham.R
import com.abra.nagham.domain.model.playback.PlaybackState
import com.abra.nagham.presentation.playing.NowPlayingEvent
import com.abra.nagham.presentation.playing.NowPlayingViewModel
import com.abra.nagham.util.formatDuration


@Composable
fun ExpandedNowPlayingContent(state: PlaybackState, onEvent: (NowPlayingEvent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = state.albumId?.let { "content://media/external/audio/albumart/$it" },
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(100)),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.artist_place_holder)
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = state.songTitle ?: "No Song Playing",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = state.artistName ?: "Unknown Artist",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Slider(
            value = state.currentPosition.toFloat(),
            onValueChange = { onEvent(NowPlayingEvent.SeekTo(it.toLong())) },
            valueRange = 0f..state.duration.toFloat(),
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = state.currentPosition.formatDuration(),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = state.duration.formatDuration(),
                style = MaterialTheme.typography.bodySmall
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { onEvent(NowPlayingEvent.ToggleShuffle) }) {
                Icon(
                    painter =
                        if (state.isShuffleEnabled)
                            painterResource(R.drawable.ic_shuffle)
                        else painterResource(R.drawable.ic_repeat),
                    contentDescription = "Shuffle"
                )
            }
            IconButton(onClick = { onEvent(NowPlayingEvent.Previous) }) {
                Icon(painterResource(R.drawable.skip_back), contentDescription = "Previous")
            }
            IconButton(onClick = { if (state.isPlaying) onEvent(NowPlayingEvent.Pause) else onEvent(NowPlayingEvent.Play) }) {
                Icon(
                    painter = if (state.isPlaying) painterResource(R.drawable.pause) else painterResource(
                        R.drawable.play
                    ),
                    contentDescription = if (state.isPlaying) "Pause" else "Play"
                )
            }
            IconButton(onClick = { onEvent(NowPlayingEvent.Next) }) {
                Icon(painterResource(R.drawable.skip_forward), contentDescription = "Next")
            }
        }
    }
}