package com.demoss.cat

import com.demoss.cat.data.dataModules
import com.demoss.cat.domain.domainModules
import com.demoss.cat.presentation.presentationModules
import org.koin.dsl.module.Module

val applicationModule: List<Module> = presentationModules +
        domainModules +
        dataModules