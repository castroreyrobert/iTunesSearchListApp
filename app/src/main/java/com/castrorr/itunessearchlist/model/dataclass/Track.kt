package com.castrorr.itunessearchlist.model.dataclass

import com.google.gson.annotations.SerializedName

data class Track (
    @SerializedName("trackId") val trackId: Double,
    @SerializedName("trackName") val trackName: String,
    @SerializedName("artworkUrl30") val artworkSmall:String,
    @SerializedName("artworkUrl60") val artworkBig:String,
    @SerializedName("trackPrice") val trackPrice: Float
    )