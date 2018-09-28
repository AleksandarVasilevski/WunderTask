package com.aleksandar.wunder.di

import com.aleksandar.wunder.ui.InfoFragment
import com.aleksandar.wunder.ui.MainActivity
import com.aleksandar.wunder.ui.MapFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, RetrofitModule::class, RepositoryModule::class, ViewModelFactoryModule::class))
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(infoFragment: InfoFragment)
    fun inject(mapFragment: MapFragment)
}