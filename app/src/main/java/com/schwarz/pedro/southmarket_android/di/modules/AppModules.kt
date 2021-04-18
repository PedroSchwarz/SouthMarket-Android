package com.schwarz.pedro.southmarket_android.di.modules

import com.schwarz.pedro.southmarket_android.providers.DataStoreManager
import com.schwarz.pedro.southmarket_android.ui.core.viewmodels.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val providersModule = module {
    single<DataStoreManager> { DataStoreManager(get()) }
}

val viewModelsModule = module {
    viewModel<SplashViewModel> { SplashViewModel(get()) }
}