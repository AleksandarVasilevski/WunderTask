package com.aleksandar.wunder.di

import android.arch.lifecycle.ViewModelProvider
import com.aleksandar.wunder.repository.MainRepository
import com.aleksandar.wunder.viewModelFactory.MainViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelFactoryModule {

    //Provides MainViewModelFactory
    @Provides
    @Singleton
    internal fun providesMainViewModelFactory(mainRepository: MainRepository): ViewModelProvider.Factory {
        return MainViewModelFactory(mainRepository)
    }
}