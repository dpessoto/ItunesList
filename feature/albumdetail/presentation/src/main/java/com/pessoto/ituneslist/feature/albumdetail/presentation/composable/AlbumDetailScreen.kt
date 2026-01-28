package com.pessoto.ituneslist.feature.albumdetail.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pessoto.ituneslist.core.presentation.collectAsEventWithLifecycle
import com.pessoto.ituneslist.feature.albumdetail.presentation.viewmodel.AlbumDetailEvent
import com.pessoto.ituneslist.feature.albumdetail.presentation.viewmodel.AlbumDetailViewEvent
import com.pessoto.ituneslist.feature.albumdetail.presentation.viewmodel.AlbumDetailViewModel
import com.pessoto.ituneslist.feature.albumslist.domain.entity.Album
import com.pessoto.ituneslist.navigation.route.LocalNavController
import com.pessoto.ituneslist.resources.R
import com.pessoto.ituneslist.ui.atom.BoxSkeleton
import com.pessoto.ituneslist.ui.atom.IconTextArguments
import com.pessoto.ituneslist.ui.atom.ItunesListIconText
import com.pessoto.ituneslist.ui.atom.ItunesListTopBar
import com.pessoto.ituneslist.ui.atom.SubcomposeAsyncImageCache
import com.pessoto.ituneslist.ui.atom.TopBarArguments
import com.pessoto.ituneslist.ui.organism.ItunesListScaffold
import com.pessoto.ituneslist.ui.organism.LoadingState
import com.pessoto.ituneslist.ui.theme.CornerShape
import com.pessoto.ituneslist.ui.theme.DefaultContentPadding
import com.pessoto.ituneslist.ui.theme.ItunesListThemePreview
import com.pessoto.ituneslist.ui.theme.Spacing
import com.pessoto.ituneslist.ui.util.TextContentType
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun AlbumDetailScreen(
    album: Album,
    viewModel: AlbumDetailViewModel = koinViewModel(parameters = { parametersOf(album) })
) {
    val navHostController = LocalNavController.current
    val viewState = viewModel.viewState.collectAsStateWithLifecycle(viewModel.currentState()).value
    viewModel.event.collectAsEventWithLifecycle {
        when (it) {
            is AlbumDetailEvent.NavigateToBack -> {
                navHostController.popBackStack()
            }
        }
    }

    AlbumDetailContent(
        album = viewState.album,
        onBackClick = {
            viewModel.dispatch(AlbumDetailViewEvent.OnBackClicked)
        }
    )
}

@Composable
private fun AlbumDetailContent(
    album: Album?,
    onBackClick: () -> Unit,
) {
    ItunesListScaffold(
        loadingState = LoadingState.Loaded,
        topBar = {
            ItunesListTopBar(
                arguments = TopBarArguments(
                    title = TextContentType.Text(album?.albumName.orEmpty()),
                    onBackClick = onBackClick,
                ),
            )
        },
        loadingContent = {
            BoxSkeleton(
                Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(DefaultContentPadding)
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(DefaultContentPadding),
            verticalArrangement = Arrangement.spacedBy(Spacing.Medium),
        ) {
            SubcomposeAsyncImageCache(
                model = album?.images?.first(),
                contentDescription = null,
                modifier = Modifier
                    .size(170.dp)
                    .clip(CornerShape.SmallCornerShape),
                loading = { _, _ ->
                    BoxSkeleton(Modifier.size(170.dp))
                },
                error = { _, _ ->
                    Image(
                        modifier = Modifier
                            .size(170.dp)
                            .clip(CornerShape.SmallCornerShape),
                        painter = painterResource(id = R.drawable.ic_warning_generic_error),
                        contentDescription = null,
                    )
                }
            )

            ItunesListIconText(
                arguments = IconTextArguments(
                    text = TextContentType.Text(album?.albumName.orEmpty()),
                    iconRes = R.drawable.ic_book_24,
                    contentDescription = null,
                    iconSize = 24.dp,
                    textStyle = MaterialTheme.typography.titleMedium,
                )
            )

            ItunesListIconText(
                arguments = IconTextArguments(
                    text = TextContentType.Text(album?.artist.orEmpty()),
                    iconRes = R.drawable.ic_person_24,
                    contentDescription = null,
                    textStyle = MaterialTheme.typography.bodyMedium,
                )
            )

            Text(
                text = album?.price.orEmpty(),
                style = MaterialTheme.typography.bodyMedium,
            )

            Text(
                text = album?.releaseDate.orEmpty(),
                style = MaterialTheme.typography.bodyMedium,
            )

            Text(
                text = album?.genre.orEmpty(),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview
@PreviewScreenSizes()
@Composable
private fun AlbumsListScreenContentPreview() {
    ItunesListThemePreview {
        AlbumDetailContent(
            album = Album(
                id = "1",
                albumName = "Lorem 1",
                artist = "Ipsum 1",
                images = listOf(""),
                price = "$9.99",
                releaseDate = "Jan 23, 2026",
                genre = "Heavy Metal"
            ),
            onBackClick = {}
        )
    }
}
