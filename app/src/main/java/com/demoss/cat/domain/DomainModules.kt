package com.demoss.cat.domain

import com.demoss.cat.domain.model.modelModule
import com.demoss.cat.domain.usecase.useCaseModule

val domainModules = listOf(
    useCaseModule,
    modelModule
)