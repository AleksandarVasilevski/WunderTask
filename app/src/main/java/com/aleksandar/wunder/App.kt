package com.aleksandar.wunder

import android.app.Application
import com.aleksandar.wunder.di.*

class App : Application() {

    var appComponent: AppComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .retrofitModule(RetrofitModule("https://s3-us-west-2.amazonaws.com/"))
                .repositoryModule(RepositoryModule())
                .viewModelFactoryModule(ViewModelFactoryModule())
                .build()
    }
}