package com.aleksandar.wunder.vo

import com.google.gson.annotations.SerializedName

data class Placemarks(@SerializedName("placemarks") val placemark: Array<Placemark>) {
}