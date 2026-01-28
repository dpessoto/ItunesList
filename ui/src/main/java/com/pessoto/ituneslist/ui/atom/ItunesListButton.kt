package com.pessoto.ituneslist.ui.atom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.pessoto.ituneslist.ui.theme.CornerShape
import com.pessoto.ituneslist.ui.theme.DefaultContentPadding
import com.pessoto.ituneslist.ui.theme.ItunesListThemePreview
import com.pessoto.ituneslist.ui.theme.Spacing
import com.pessoto.ituneslist.ui.util.TextContentType

@Composable
fun ItunesListButton(
    modifier: Modifier = Modifier,
    text: TextContentType,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        shape = CornerShape.SmallCornerShape,
        onClick = onClick,
    ) {
        Text(
            text = text.content().orEmpty(),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview(
    backgroundColor = 0xFFFFFFFF,
    showBackground = true,
)
@Composable
private fun ItunesListButtonPreview() {
    ItunesListThemePreview {
        Column(Modifier.padding(DefaultContentPadding)) {
            ItunesListButton(
                text = TextContentType.Text("Ok"),
                onClick = {}
            )
            Spacer(modifier = Modifier.height(Spacing.Medium))
            ItunesListButton(
                modifier = Modifier.fillMaxWidth(),
                text = TextContentType.Text("Ok"),
                onClick = {}
            )

        }
    }
}
