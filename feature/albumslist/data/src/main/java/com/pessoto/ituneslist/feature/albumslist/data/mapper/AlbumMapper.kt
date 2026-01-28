package com.pessoto.ituneslist.feature.albumslist.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.pessoto.ituneslist.core.data.mapper.Mapper
import com.pessoto.ituneslist.feature.albumslist.data.model.AlbumEntryDto
import com.pessoto.ituneslist.feature.albumslist.domain.entity.Album
import java.text.DateFormat
import java.time.OffsetDateTime
import java.util.Date
import kotlin.text.category

internal class AlbumMapper : Mapper<AlbumEntryDto, Album> {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun map(source: AlbumEntryDto): Album = with(source) {
        return Album(
            id = id.attributes.value,
            albumName = name.value,
            artist = artist.value,
            images = images.map { it.url },
            price = price.value,
            releaseDate = formatIsoDateToLocal(releaseDate.value),
            genre = category.attributes.term,
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun formatIsoDateToLocal(isoDate: String): String {
        val parsedDate = OffsetDateTime.parse(isoDate)
        val date = Date.from(parsedDate.toInstant())
        return DateFormat.getDateInstance(DateFormat.MEDIUM).format(date)
    }
}
