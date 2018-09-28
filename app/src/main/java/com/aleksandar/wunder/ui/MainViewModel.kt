package com.aleksandar.wunder.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.aleksandar.wunder.repository.MainRepository
import com.aleksandar.wunder.vo.Placemark
import com.aleksandar.wunder.vo.Placemarks

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    //Holds placemarks from the last call
    private lateinit var placemarksLiveData: MutableLiveData<Placemarks>

    //Holds placemark from selected place
    private var placemarkLiveData: MutableLiveData<Placemark> = MutableLiveData()

    /**
     * Return the last state of the placemarks as MutableLiveData
     */
    fun getPlacemarks(): MutableLiveData<Placemarks>{
        placemarksLiveData = mainRepository.fetchPlacemarks()
        return placemarksLiveData
    }

    /**
     * Return the last state of the placemark as MutableLiveData
     */
    fun getPlacemark(): MutableLiveData<Placemark>{
        return placemarkLiveData
    }
}