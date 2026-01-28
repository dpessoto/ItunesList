package com.pessoto.ituneslist.ui.di

import android.content.Context
import coil.ImageLoader
import coil.disk.DiskCache
import coil.memory.MemoryCache
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

internal const val IMAGE_CACHE_DIR = "image_cache_ituneslist"
internal const val MEMORY_CACHE_SIZE_PERCENT = 0.20
internal const val IMAGE_CACHE_SIZE_PERCENT = 0.02

internal val uiModule = module {
    single {
        val context: Context = androidContext()
        ImageLoader.Builder(context)
            .memoryCache {
                MemoryCache.Builder(context)
                    .maxSizePercent(MEMORY_CACHE_SIZE_PERCENT)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(context.cacheDir.resolve(IMAGE_CACHE_DIR))
                    .maxSizePercent(IMAGE_CACHE_SIZE_PERCENT)
                    .build()
            }
            .build()
    }
}
