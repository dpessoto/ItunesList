package com.pessoto.ituneslist.ui.atom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImagePainter.State
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageScope
import com.pessoto.ituneslist.resources.R
import com.pessoto.ituneslist.ui.theme.DefaultContentPadding
import com.pessoto.ituneslist.ui.theme.ItunesListThemePreview
import org.koin.compose.getKoin

@Composable
fun SubcomposeAsyncImageCache(
    model: Any?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    imageLoader: ImageLoader = getKoin().get(),
    loading: (@Composable (SubcomposeAsyncImageScope, State.Loading) -> Unit)? = null,
    error: (@Composable (SubcomposeAsyncImageScope, State.Error) -> Unit)? = null,
) {
    SubcomposeAsyncImage(
        model = model,
        contentDescription = contentDescription,
        imageLoader = imageLoader,
        modifier = modifier,
        loading = loading,
        error = error,
    )
}

@Preview(
    backgroundColor = 0xFFFFFFFF,
    showBackground = true,
)
@Composable
private fun AsyncImageCachePreview() {
    ItunesListThemePreview {
        Column(Modifier.padding(DefaultContentPadding)) {
            SubcomposeAsyncImageCache(
                model = R.drawable.ic_warning_generic_error,
                contentDescription = "Preview image",
                modifier = Modifier.size(50.dp),
            )
        }
    }
}
