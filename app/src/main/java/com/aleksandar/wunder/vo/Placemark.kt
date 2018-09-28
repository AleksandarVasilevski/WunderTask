package com.aleksandar.wunder.vo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Placemark(
        @SerializedName("address") val address: String,
        @SerializedName("coordinates") val coordinates: Array<Double>,
        @SerializedName("engineType") val engineType: String,
        @SerializedName("exterior") val exterior: String,
        @SerializedName("fuel") val fuel: Int,
        @SerializedName("interior") val interior: String,
        @SerializedName("name") val name: String,
        @SerializedName("vin") val vin: String) {
}