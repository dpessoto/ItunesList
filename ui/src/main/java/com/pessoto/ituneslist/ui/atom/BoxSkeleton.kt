package com.pessoto.ituneslist.ui.atom

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pessoto.ituneslist.ui.extension.shimmerEffect
import com.pessoto.ituneslist.ui.theme.CornerShape
import com.pessoto.ituneslist.ui.theme.DefaultContentPadding
import com.pessoto.ituneslist.ui.theme.ItunesListThemePreview
import com.pessoto.ituneslist.ui.theme.Spacing

@Composable
fun BoxSkeleton(
    modifier: Modifier = Modifier,
    shape: Shape = CornerShape.SmallCornerShape,
) {
    Box(modifier.shimmerEffect(shape))
}

@Preview(
    backgroundColor = 0xFFFFFFFF,
    showBackground = true,
)
@Composable
private fun BoxSkeletonPreview() {
    ItunesListThemePreview {
        Column(Modifier.padding(DefaultContentPadding)) {
            BoxSkeleton(Modifier.size(91.dp, 30.dp))
            Spacer(modifier = Modifier.height(Spacing.Small))
            BoxSkeleton(Modifier.size(50.dp, 50.dp))
        }
    }
}
