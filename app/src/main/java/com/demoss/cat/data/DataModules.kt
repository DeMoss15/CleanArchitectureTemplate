package com.demoss.cat.data

import com.demoss.cat.data.local.localDataModules
import com.demoss.cat.data.remote.remoteDataModules
import com.demoss.cat.data.repository.repositoryDataModule

val dataModules = localDataModules +
        remoteDataModules +
        repositoryDataModule