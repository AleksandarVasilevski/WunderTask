package com.aleksandar.wunder.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

//Application module that provide context to other modules
@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    internal fun provideApplication(): Application {
        return application
    }
}