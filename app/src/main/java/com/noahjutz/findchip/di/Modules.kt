package com.noahjutz.findchip.di

import com.noahjutz.findchip.NavGraphViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModule = module {
    viewModel {
        NavGraphViewModel(application = androidApplication())
    }
}