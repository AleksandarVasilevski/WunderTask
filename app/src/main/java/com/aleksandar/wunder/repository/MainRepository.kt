package com.aleksandar.wunder.repository

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.aleksandar.wunder.network.RestApi
import com.aleksandar.wunder.vo.Placemarks
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainRepository(val retrofit: Retrofit) {

    val TAG = "MAIN_REPOSITORY"

    //Holds placemarks from the last call
    var placemarksLiveData: MutableLiveData<Placemarks>

    init {
        placemarksLiveData = MutableLiveData()
    }

    /**
     * Makes retrofit call to the server and receives the results
     */
    fun fetchPlacemarks(): MutableLiveData<Placemarks>{
        val call: Call<Placemarks> = retrofit.create(RestApi::class.java).getPlacemarks()
        call.enqueue(object : Callback<Placemarks> {
            override fun onResponse(call: Call<Placemarks>, response: Response<Placemarks>) {
                placemarksLiveData.value = response.body()
                Log.d(TAG, "onResponse " + response.body()!!.placemark[0])
            }
            override fun onFailure(call: Call<Placemarks>, t: Throwable) {
                Log.d(TAG, "onFailure " + t.message)
            }
        })
        return placemarksLiveData
    }
}