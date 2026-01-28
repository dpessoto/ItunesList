package com.pessoto.ituneslist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.pessoto.ituneslist.feature.albumdetail.presentation.composable.AlbumDetailScreen
import com.pessoto.ituneslist.feature.albumslist.domain.entity.Album
import com.pessoto.ituneslist.feature.albumslist.presentation.composable.AlbumsListScreen
import com.pessoto.ituneslist.navigation.route.ItunesListScreen
import com.pessoto.ituneslist.navigation.route.LocalNavController

@Composable
fun ItunesListNavigation() {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(
            navController = navController,
            startDestination = ItunesListScreen.AlbumsList,
        ) {
            composable<ItunesListScreen.AlbumsList> {
                AlbumsListScreen()
            }

            composable<ItunesListScreen.AlbumDetail> { backStackEntry ->
                val album = backStackEntry.toRoute<ItunesListScreen.AlbumDetail>()
                AlbumDetailScreen(
                    Album(
                        id = album.id,
                        albumName = album.albumName,
                        artist = album.artist,
                        images = listOf(album.image),
                        price = album.price,
                        releaseDate = album.releaseDate,
                        genre = album.genre
                    ),
                )
            }
        }
    }
}
