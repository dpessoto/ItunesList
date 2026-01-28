package com.pessoto.ituneslist.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.res.stringResource
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
sealed class TextContentType {
    data class Text(val text: String? = null) : TextContentType()

    data class TextResource(
        val textResId: Int,
        val formatArgs: ImmutableList<TextContentType> = persistentListOf()
    ) : TextContentType()

    @Composable
    fun content(): String? {
        return when (this) {
            is Text -> this.text
            is TextResource -> {
                val resolvedArgs = formatArgs.mapNotNull { it.content() }.toTypedArray()
                stringResource(id = this.textResId, *resolvedArgs)
            }
        }
    }
}
