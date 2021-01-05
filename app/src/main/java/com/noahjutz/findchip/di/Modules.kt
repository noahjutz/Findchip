package com.noahjutz.findchip.di

import android.bluetooth.BluetoothAdapter
import com.noahjutz.findchip.ui.NavGraphViewModel
import com.noahjutz.findchip.ui.entry_point.FindchipAppViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModule = module {
    single {
        BluetoothAdapter.getDefaultAdapter()
    }

    viewModel {
        NavGraphViewModel(application = androidApplication())
    }

    viewModel {
        FindchipAppViewModel(
            bluetoothAdapter = get(),
            application = androidApplication()
        )
    }
}