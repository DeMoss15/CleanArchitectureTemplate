package com.demoss.cat.data.local

import com.demoss.cat.data.local.repository.localRepositoryModule

val localDataModules = listOf(
    dbModule,
    localRepositoryModule
)