package com.abra.nagham.presentation.songs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abra.nagham.domain.model.music.Song

@Composable
fun SongItem(song: Song, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = song.title, style = MaterialTheme.typography.titleMedium)
            Text(text = "Artist ID: ${song.artistId}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview
@Composable
private fun SongItemPreview() {
    Surface {
        SongItem(
            Song(
                songId = 1L,
                title = "Ritalin",
                artistId = 1,
                albumId = 1,
                duration = 450000L,
                filePath = "/path/to/song.mp3"
            )
        ) {

        }
    }
}