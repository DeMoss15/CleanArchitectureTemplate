package com.demoss.cat.data.remote

import com.demoss.cat.data.remote.api.apiModule
import com.demoss.cat.data.remote.repository.remoteRepositoryModule

val remoteDataModules = listOf(
    apiModule,
    remoteRepositoryModule,
    networkModule
)