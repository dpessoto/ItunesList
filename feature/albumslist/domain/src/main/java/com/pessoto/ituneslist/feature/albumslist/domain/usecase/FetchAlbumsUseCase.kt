package com.pessoto.ituneslist.feature.albumslist.domain.usecase

import com.pessoto.ituneslist.core.domain.usecase.FlowUseCase
import com.pessoto.ituneslist.feature.albumslist.domain.entity.Album

interface FetchAlbumsUseCase : FlowUseCase<Int, List<Album>>
