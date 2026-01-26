package com.pessoto.ituneslist.core.data.mapper

interface Mapper<S, T> {
    fun map(source: S): T
}
