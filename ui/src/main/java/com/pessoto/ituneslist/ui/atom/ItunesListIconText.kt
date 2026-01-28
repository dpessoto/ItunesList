package com.pessoto.ituneslist.ui.atom

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.pessoto.ituneslist.resources.R
import com.pessoto.ituneslist.ui.theme.Components
import com.pessoto.ituneslist.ui.theme.ItunesListThemePreview
import com.pessoto.ituneslist.ui.theme.Spacing
import com.pessoto.ituneslist.ui.util.TextContentType

@Immutable
data class IconTextArguments(
    val text: TextContentType,
    @DrawableRes val iconRes: Int,
    val contentDescription: String? = null,
    val iconSize: Dp = Components.Large,
    val textStyle: TextStyle,
)

@Composable
fun ItunesListIconText(
    modifier: Modifier = Modifier,
    arguments: IconTextArguments,
) = with(arguments) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Icon(
            modifier = Modifier.size(iconSize),
            painter = painterResource(id = iconRes),
            contentDescription = contentDescription,
            tint = Color.Black
        )
        Spacer(modifier = Modifier.width(Spacing.Mini))
        Text(
            text = text.content().orEmpty(),
            style = textStyle,
            modifier = Modifier.weight(1f, fill = false)
        )
    }
}

@Preview(
    backgroundColor = 0xFFFFFFFF,
    showBackground = true,
)
@Composable
private fun ItunesListIconTextPreview() {
    ItunesListThemePreview {
        ItunesListIconText(
            arguments = IconTextArguments(
                text = TextContentType.Text("Lorem Ipsum"),
                iconRes = R.drawable.ic_book_24,
                textStyle = MaterialTheme.typography.bodyMedium,
            ),
        )
    }
}
