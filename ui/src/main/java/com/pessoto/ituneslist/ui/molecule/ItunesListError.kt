package com.pessoto.ituneslist.ui.molecule

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.pessoto.ituneslist.resources.R
import com.pessoto.ituneslist.ui.atom.ItunesListButton
import com.pessoto.ituneslist.ui.theme.ItunesListThemePreview
import com.pessoto.ituneslist.ui.theme.Padding
import com.pessoto.ituneslist.ui.theme.Spacing
import com.pessoto.ituneslist.ui.util.TextContentType

@Immutable
data class ItunesListErrorArguments(
    @DrawableRes val iconRes: Int,
    val title: TextContentType,
    val description: TextContentType,
    val buttonText: TextContentType,
    val onClick: () -> Unit,
)

@Composable
fun ItunesListError(
    modifier: Modifier = Modifier,
    args: ItunesListErrorArguments,
) = with(args) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = "",
        )
        Spacer(modifier = Modifier.height(Spacing.Small))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Padding.Horizontal),
            text = title.content().orEmpty(),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(Spacing.Small))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Padding.Horizontal),
            text = description.content().orEmpty(),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(Spacing.Medium))
        ItunesListButton(
            modifier = Modifier.padding(horizontal = Padding.Horizontal),
            text = buttonText,
            onClick = onClick,
        )
    }
}

@Preview()
@Composable
private fun ItunesListErrorPreview() {
    ItunesListThemePreview {
        ItunesListError(
            args = ItunesListErrorArguments(
                iconRes = R.drawable.ic_warning_generic_error,
                title = TextContentType.Text("Lorem Ipsum"),
                description = TextContentType.Text("Lorem Ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."),
                buttonText = TextContentType.Text("Ok"),
                onClick = {},
            )
        )
    }
}
