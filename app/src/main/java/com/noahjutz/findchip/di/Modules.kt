package com.noahjutz.findchip.di

import android.bluetooth.BluetoothAdapter
import com.noahjutz.findchip.ui.device_details.DeviceDetailsViewModel
import com.noahjutz.findchip.ui.device_list.DeviceListViewModel
import com.noahjutz.findchip.ui.entry_point.FindchipAppViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModule = module {
    single {
        BluetoothAdapter.getDefaultAdapter()
    }

    viewModel {
        FindchipAppViewModel(
            bluetoothAdapter = get(),
            application = androidApplication()
        )
    }

    viewModel { (address: String) ->
        DeviceDetailsViewModel(address = address)
    }

    viewModel {
        DeviceListViewModel(bluetoothAdapter = get())
    }
}