package com.castrorr.itunessearchlist.model

import com.castrorr.itunessearchlist.model.dataclass.Track
import com.google.gson.annotations.SerializedName
import io.reactivex.internal.operators.observable.ObservableFromArray

data class SearchListResponse(@SerializedName("results") val result: ObservableFromArray<Track>)