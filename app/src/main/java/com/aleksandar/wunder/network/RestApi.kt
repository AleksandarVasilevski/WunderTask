package com.aleksandar.wunder.network

import com.aleksandar.wunder.vo.Placemarks
import retrofit2.Call
import retrofit2.http.GET

interface RestApi {

    @GET("/wunderbucket/locations.json")
    fun getPlacemarks(): Call<Placemarks>
}