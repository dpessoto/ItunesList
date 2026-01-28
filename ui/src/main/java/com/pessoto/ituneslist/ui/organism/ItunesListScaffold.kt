package com.pessoto.ituneslist.ui.organism

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pessoto.ituneslist.resources.R
import com.pessoto.ituneslist.ui.atom.BoxSkeleton
import com.pessoto.ituneslist.ui.molecule.ItunesListErrorArguments
import com.pessoto.ituneslist.ui.molecule.ItunesListError
import com.pessoto.ituneslist.ui.theme.DefaultContentPadding
import com.pessoto.ituneslist.ui.theme.ItunesListThemePreview
import com.pessoto.ituneslist.ui.theme.Spacing
import com.pessoto.ituneslist.ui.util.TextContentType

sealed class LoadingState {
    data object Loaded : LoadingState()
    data object Loading : LoadingState()

    fun isLoading() = this == Loading
}

@Composable
fun ItunesListScaffold(
    modifier: Modifier = Modifier,
    errorArgument: ItunesListErrorArguments? = null,
    loadingState: LoadingState,
    loadingContent: @Composable (PaddingValues) -> Unit,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets.systemBars),
    ) { innerPadding ->
        when {
            errorArgument != null -> ItunesListError(
                modifier = Modifier.padding(innerPadding),
                args = errorArgument
            )
            loadingState.isLoading() -> loadingContent(innerPadding)
            else -> content(innerPadding)
        }
    }
}

@Preview
@Composable
private fun ItunesListScaffoldScaffoldPreview() {
    ItunesListThemePreview {
        ItunesListScaffold(
            modifier = Modifier,
            errorArgument = null,
            loadingState = LoadingState.Loaded,
            loadingContent = { _ -> },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .padding(DefaultContentPadding)
                ) {
                    Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
                    Spacer(Modifier.height(Spacing.Small))
                    Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
                    Spacer(Modifier.height(Spacing.Small))
                    Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
                    Spacer(Modifier.height(Spacing.Small))
                    Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
                }
            },
        )
    }
}

@Preview
@Composable
private fun ItunesListScaffoldLoadingPreview() {
    ItunesListThemePreview {
        ItunesListScaffold(
            modifier = Modifier,
            errorArgument = null,
            loadingState = LoadingState.Loading,
            loadingContent = { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .padding(DefaultContentPadding)
                ) {
                    BoxSkeleton(Modifier.size(91.dp, 30.dp))
                    Spacer(modifier = Modifier.height(Spacing.Small))
                    BoxSkeleton(Modifier.size(50.dp, 50.dp))
                    Spacer(modifier = Modifier.height(Spacing.Small))
                    BoxSkeleton(Modifier.size(91.dp, 30.dp))
                    Spacer(modifier = Modifier.height(Spacing.Small))
                    BoxSkeleton(Modifier.size(50.dp, 50.dp))
                }
            },
            content = { _ -> },
        )
    }
}

@Preview
@Composable
private fun ItunesListScaffoldErrorPreview() {
    ItunesListThemePreview {
        ItunesListScaffold(
            modifier = Modifier,
            errorArgument = ItunesListErrorArguments(
                    iconRes = R.drawable.ic_warning_generic_error,
                    title = TextContentType.Text("Error Title"),
                    description = TextContentType.Text("This is an error message to inform the user about what went wrong."),
                    buttonText = TextContentType.Text("Retry"),
                onClick = {}
            ),
            loadingState = LoadingState.Loaded,
            loadingContent = { _ -> },
            content = { _ -> },
        )
    }
}
