package com.castrorr.itunessearchlist.model.dataclass

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class Results(
    @field:Json (name = "results") val results : List<Track>,
    @field:Json(name = "resultCount") val resultCount: Int
)
data class Track (
    @field:Json(name = "trackId") val trackId: Double,
    @field:Json(name = "trackName") val trackName: String,
    @field:Json(name = "artworkUrl30") val artworkSmall:String,
    @field:Json(name = "artworkUrl60") val artworkBig:String,
    @field:Json(name = "trackPrice") val trackPrice: Float
    )