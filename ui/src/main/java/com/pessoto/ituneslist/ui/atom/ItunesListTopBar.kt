package com.pessoto.ituneslist.ui.atom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.pessoto.ituneslist.resources.R
import com.pessoto.ituneslist.ui.theme.Components
import com.pessoto.ituneslist.ui.theme.ItunesListThemePreview
import com.pessoto.ituneslist.ui.util.TextContentType

@Immutable
data class TopBarArguments(
    val title: TextContentType? = null,
    val onBackClick: (() -> Unit)? = null,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItunesListTopBar(modifier: Modifier = Modifier, arguments: TopBarArguments) = with(arguments) {
    Column {
        TopAppBar(
            navigationIcon = {
                onBackClick?.let { navIcon ->
                    IconButton(
                        modifier = Modifier.size(Components.Extra),
                        onClick = { onBackClick.invoke() },
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "",
                            tint = Color.Black,
                            modifier = Modifier.size(Components.Large),
                        )
                    }
                }
            },
            title = {
                arguments.title?.content()?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                scrolledContainerColor = MaterialTheme.colorScheme.background,
            ),
            modifier = modifier,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ItunesListTopBarWithActionPreview() {
    ItunesListThemePreview {
        ItunesListTopBar(
            arguments =
                TopBarArguments(
                    title = TextContentType.Text("Lorem"),
                    onBackClick = {},
                ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ItunesListTopBarWithoutActionPreview() {
    ItunesListThemePreview {
        ItunesListTopBar(
            arguments =
                TopBarArguments(
                    title = TextContentType.Text("Lorem"),
                ),
        )
    }
}
