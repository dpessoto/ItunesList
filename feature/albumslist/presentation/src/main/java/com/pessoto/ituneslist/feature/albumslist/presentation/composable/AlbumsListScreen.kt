package com.pessoto.ituneslist.feature.albumslist.presentation.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.pessoto.ituneslist.core.presentation.collectAsEventWithLifecycle
import com.pessoto.ituneslist.feature.albumslist.presentation.viewmodel.AlbumsListEvent
import com.pessoto.ituneslist.feature.albumslist.presentation.viewmodel.AlbumsListViewEvent.OnInit
import com.pessoto.ituneslist.feature.albumslist.presentation.viewmodel.AlbumsListViewModel
import com.pessoto.ituneslist.resources.R
import com.pessoto.ituneslist.ui.atom.BoxSkeleton
import com.pessoto.ituneslist.ui.molecule.AlbumItem
import com.pessoto.ituneslist.ui.molecule.AlbumItemArguments
import com.pessoto.ituneslist.ui.molecule.ItunesListErrorArguments
import com.pessoto.ituneslist.ui.organism.ItunesListScaffold
import com.pessoto.ituneslist.ui.organism.LoadingState
import com.pessoto.ituneslist.ui.theme.DefaultContentPadding
import com.pessoto.ituneslist.ui.theme.ItunesListThemePreview
import com.pessoto.ituneslist.ui.theme.Padding
import com.pessoto.ituneslist.ui.theme.Spacing
import com.pessoto.ituneslist.ui.util.TextContentType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.koin.androidx.compose.koinViewModel

private const val GRID_CELLS_COMPACT = 1
private const val GRID_CELLS_EXPANDED = 2

@Composable
fun AlbumsListScreen(
    viewModel: AlbumsListViewModel = koinViewModel()
) {
    val viewState = viewModel.viewState.collectAsStateWithLifecycle(viewModel.currentState()).value
    viewModel.event.collectAsEventWithLifecycle {
        when (it) {
            is AlbumsListEvent.NavigateToDetail -> Unit
        }
    }

    LaunchedEffect(Unit) {
        viewModel.dispatch(OnInit)
    }

    AlbumsListScreenContent(
        loadingState = viewState.loadingState,
        errorArgument = viewState.errorArgument,
        albums = viewState.albums,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun AlbumsListScreenContent(
    loadingState: LoadingState,
    errorArgument: ItunesListErrorArguments?,
    albums: ImmutableList<AlbumItemArguments>,
    windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass,
) {
    val gridCells = when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> GRID_CELLS_COMPACT
        else -> GRID_CELLS_EXPANDED
    }

    ItunesListScaffold(
        loadingState = loadingState,
        errorArgument = errorArgument,
        loadingContent = { AlbumsSkeleton(Modifier.padding(it), gridCells) },
    ) { paddingValues ->
        LazyVerticalGrid(
            state = rememberLazyGridState(),
            columns = GridCells.Fixed(gridCells),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = Padding.Horizontal),
            horizontalArrangement = Arrangement.spacedBy(Spacing.Small),
            verticalArrangement = Arrangement.spacedBy(Spacing.Small)
        ) {
            items(albums, key = { it.id }) { album ->
                AlbumItem(modifier = Modifier.fillMaxWidth(), arguments = album)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun AlbumsSkeleton(modifier: Modifier, gridCells: Int) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(gridCells),
        modifier = modifier
            .fillMaxSize()
            .padding(DefaultContentPadding),
        verticalArrangement = Arrangement.spacedBy(Spacing.Small),
        horizontalArrangement = Arrangement.spacedBy(Spacing.Small)
    ) {
        items(10) {
            BoxSkeleton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(88.dp)
            )
        }
    }
}

@Preview
@PreviewScreenSizes()
@Composable
private fun AlbumsListScreenContentPreview() {
    ItunesListThemePreview {
        AlbumsListScreenContent(
            LoadingState.Loaded,
            null,
            persistentListOf(
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
                AlbumItemArguments(
                    id = "3",
                    albumName = TextContentType.Text("Lorem 3"),
                    artist = TextContentType.Text("Ipsum 3"),
                    albumImageUrl = "https://is1-ssl.mzstatic.com/image/thumb/Music211/v4/e1/15/42/e1154273-8ecd-5702-e6e6-597f28001681/25UMGIM82363.rgb.jpg/55x55bb.png",
                    onClick = {},
                ),
                AlbumItemArguments(
                    id = "4",
                    albumName = TextContentType.Text("Lorem 4"),
                    artist = TextContentType.Text("Ipsum 4"),
                    albumImageUrl = "https://is1-ssl.mzstatic.com/image/thumb/Music211/v4/e1/15/42/e1154273-8ecd-5702-e6e6-597f28001681/25UMGIM82363.rgb.jpg/55x55bb.png",
                    onClick = {},
                ),
                AlbumItemArguments(
                    id = "5",
                    albumName = TextContentType.Text("Lorem 5"),
                    artist = TextContentType.Text("Ipsum 5"),
                    albumImageUrl = "https://is1-ssl.mzstatic.com/image/thumb/Music211/v4/e1/15/42/e1154273-8ecd-5702-e6e6-597f28001681/25UMGIM82363.rgb.jpg/55x55bb.png",
                    onClick = {},
                ),
                AlbumItemArguments(
                    id = "6",
                    albumName = TextContentType.Text("Lorem 6"),
                    artist = TextContentType.Text("Ipsum 6"),
                    albumImageUrl = "https://is1-ssl.mzstatic.com/image/thumb/Music211/v4/e1/15/42/e1154273-8ecd-5702-e6e6-597f28001681/25UMGIM82363.rgb.jpg/55x55bb.png",
                    onClick = {},
                ),
                AlbumItemArguments(
                    id = "7",
                    albumName = TextContentType.Text("Lorem 7"),
                    artist = TextContentType.Text("Ipsum 7"),
                    albumImageUrl = "https://is1-ssl.mzstatic.com/image/thumb/Music211/v4/e1/15/42/e1154273-8ecd-5702-e6e6-597f28001681/25UMGIM82363.rgb.jpg/55x55bb.png",
                    onClick = {},
                ),
            )
        )
    }
}

@Preview
@PreviewScreenSizes()
@Composable
private fun AlbumsListScreenContentLoadingPreview() {
    ItunesListThemePreview {
        AlbumsListScreenContent(
            LoadingState.Loading,
            null,
            persistentListOf(),
        )
    }
}

@Preview
@PreviewScreenSizes()
@Composable
private fun AlbumsListScreenContentErrorPreview() {
    ItunesListThemePreview {
        AlbumsListScreenContent(
            LoadingState.Loaded,
            ItunesListErrorArguments(
                iconRes = R.drawable.ic_warning_generic_error,
                title = TextContentType.Text("Lorem Ipsum"),
                description = TextContentType.Text("Lorem Ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."),
                buttonText = TextContentType.Text("Ok"),
                onClick = {},
            ),
            persistentListOf(),
        )
    }
}
