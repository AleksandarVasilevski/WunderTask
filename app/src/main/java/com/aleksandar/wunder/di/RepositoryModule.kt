package com.aleksandar.wunder.di

import com.aleksandar.wunder.repository.MainRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RepositoryModule {

    //Provides MainRepository
    @Provides
    @Singleton
    internal fun provideMainRepository(retrofit: Retrofit): MainRepository {
        return MainRepository(retrofit)
    }
}