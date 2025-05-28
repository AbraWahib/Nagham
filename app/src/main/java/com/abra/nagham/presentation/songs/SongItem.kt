package com.abra.nagham.presentation.songs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abra.nagham.util.formatDuration
import com.abra.nagham.domain.model.music.Song
import com.abra.nagham.domain.model.music.SongWithArtist
import com.abra.nagham.presentation.ui.theme.NaghamTheme

@Composable
fun SongItem(songWithArtist: SongWithArtist, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() }
    ) {
        Column(Modifier
            .weight(1f)
            .padding(horizontal = 8.dp)) {
            Text(
                text = songWithArtist.song.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                )
            Spacer(Modifier.height(4.dp))
            Text(
                text = songWithArtist.artistName,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.alpha(.7f)
            )
        }
        Text(
            songWithArtist.song.duration.formatDuration(),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
private fun SongItemPreview() {
    NaghamTheme {
        Surface {
            SongItem(
                SongWithArtist(
                    Song(
                        songId = 1L,
                        title = "Tooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo much",
                        artistId = 1,
                        albumId = 1,
                        duration = 450000L,
                        filePath = "/path/to/song.mp3"
                    ),
                    artistName = "Synaptik"
                )
            ) {

            }
        }
    }
}