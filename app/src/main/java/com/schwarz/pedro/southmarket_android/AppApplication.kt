package com.schwarz.pedro.southmarket_android

import android.app.Application
import com.schwarz.pedro.southmarket_android.di.modules.providersModule
import com.schwarz.pedro.southmarket_android.di.modules.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(providersModule, viewModelsModule)
        }
    }
}