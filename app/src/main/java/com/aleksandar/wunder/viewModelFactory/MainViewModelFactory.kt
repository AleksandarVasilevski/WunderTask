package com.aleksandar.wunder.viewModelFactory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.aleksandar.wunder.repository.MainRepository
import com.aleksandar.wunder.ui.MainViewModel
import java.lang.IllegalArgumentException

class MainViewModelFactory(private var mainRepository: MainRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            MainViewModel(mainRepository) as T
        }else{
            throw IllegalArgumentException("Unknown ViewModel Class") as Throwable
        }
    }
}