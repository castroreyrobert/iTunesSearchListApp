package com.castrorr.itunessearchlist.model.dataclass

import com.squareup.moshi.Json

data class Results(
    @field:Json (name = "results") val results : List<Track>,
    @field:Json(name = "resultCount") val resultCount: Int
)
fun Results.mapToDomain() : Results = Results(results, resultCount)


data class Track (
    @field:Json(name = "trackId") val trackId: Double,
    @field:Json(name = "trackName") val trackName: String,
    @field:Json(name = "artworkUrl60") val artworkSmall:String,
    @field:Json(name = "artworkUrl100") val artworkBig:String,
    @field:Json(name = "trackPrice") val trackPrice: Float,
    @field:Json(name = "primaryGenreName") val genre: String,
    @field:Json(name = "longDescription") val longDescription: String
)
//fun List<Track>.mapToTrackList() : List<Track> = map { it.mapToDomain() }
//fun Track.mapToDomain(): Track = Track(trackId, trackName, artworkSmall, artworkBig, trackPrice, genre, longDescription)