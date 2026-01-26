package com.pessoto.ituneslist.core.domain.usecase

import kotlinx.coroutines.flow.Flow

typealias FlowUseCase<P, R> = (P) -> Flow<R>
