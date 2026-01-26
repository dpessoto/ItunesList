package com.pessoto.ituneslist.core.data.di

import com.pessoto.ituneslist.core.di.ModuleProvider

class CoreDataModuleProvider: ModuleProvider() {

    override val modules = listOf(CoreDataModule)
}
