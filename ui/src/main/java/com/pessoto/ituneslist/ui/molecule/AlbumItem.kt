package com.pessoto.ituneslist.ui.molecule

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pessoto.ituneslist.resources.R
import com.pessoto.ituneslist.ui.atom.BoxSkeleton
import com.pessoto.ituneslist.ui.atom.SubcomposeAsyncImageCache
import com.pessoto.ituneslist.ui.theme.CornerShape
import com.pessoto.ituneslist.ui.theme.DefaultContentPadding
import com.pessoto.ituneslist.ui.theme.Elevation
import com.pessoto.ituneslist.ui.theme.ItunesListThemePreview
import com.pessoto.ituneslist.ui.theme.Spacing
import com.pessoto.ituneslist.ui.util.TextContentType

@Immutable
data class AlbumItemArguments(
    val id: String,
    val albumName: TextContentType,
    val artist: TextContentType,
    val albumImageUrl: Any,
    val onClick: () -> Unit,
)

@Composable
fun AlbumItem(
    modifier: Modifier = Modifier,
    arguments: AlbumItemArguments
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { arguments.onClick },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = CornerShape.SmallCornerShape,
        elevation = CardDefaults.cardElevation(Elevation.Tiny)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.inversePrimary,
                        shape = CornerShape.SmallCornerShape
                    ), contentAlignment = Alignment.Center
            ) {
                SubcomposeAsyncImageCache(
                    model = arguments.albumImageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CornerShape.SmallCornerShape),
                    loading = { _, _ ->
                        BoxSkeleton(Modifier.fillMaxSize())
                    },
                    error = { _, _ ->
                        Image(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CornerShape.SmallCornerShape),
                            painter = painterResource(id = R.drawable.ic_warning_generic_error),
                            contentDescription = null,
                        )
                    }

                )
            }
            Spacer(modifier = Modifier.width(Spacing.Small))
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_book_24),
                        contentDescription = null,
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(Spacing.Mini))
                    Text(
                        text = arguments.albumName.content().orEmpty(),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(Spacing.Small))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.ic_person_24),
                        contentDescription = null,
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(Spacing.Mini))
                    Text(
                        text = arguments.artist.content().orEmpty(),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Spacer(modifier = Modifier.width(Spacing.Small))
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right_24),
                contentDescription = null,
                tint = Color.Black
            )
        }
    }
}

@Preview
@Composable
private fun RepositoriesScreenContentPreview() {
    ItunesListThemePreview {
        val items = listOf(
            AlbumItemArguments(
                id = "1",
                albumName = TextContentType.Text("Lorem 1"),
                artist = TextContentType.Text("Ipsum 1"),
                albumImageUrl = "https://is1-ssl.mzstatic.com/image/thumb/Music211/v4/e1/15/42/e1154273-8ecd-5702-e6e6-597f28001681/25UMGIM82363.rgb.jpg/55x55bb.png",
                onClick = {},
            ),
            AlbumItemArguments(
                id = "2",
                albumName = TextContentType.Text("Lorem 2"),
                artist = TextContentType.Text("Ipsum 2"),
                albumImageUrl = "https://is1-ssl.mzstatic.com/image/thumb/Music211/v4/e1/15/42/e1154273-8ecd-5702-e6e6-597f28001681/25UMGIM82363.rgb.jpg/55x55bb.png",
                onClick = {},
            ),
        )
        Column(Modifier.padding(DefaultContentPadding)) {
            items.forEach {
                AlbumItem(arguments = it)
                Spacer(Modifier.height(Spacing.Small))
            }
        }
    }
}
