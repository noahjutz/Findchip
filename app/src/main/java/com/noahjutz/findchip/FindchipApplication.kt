package com.noahjutz.findchip

import android.app.Application
import com.noahjutz.findchip.di.koinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FindchipApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(koinModule)
            androidContext(this@FindchipApplication)
        }
    }
}