package com.noahjutz.findchip.di

import com.noahjutz.findchip.NavGraphViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModule = module {
    viewModel<NavGraphViewModel> {
        NavGraphViewModel()
    }
}