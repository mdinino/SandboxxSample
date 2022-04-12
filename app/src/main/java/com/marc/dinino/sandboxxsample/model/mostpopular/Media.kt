package com.marc.dinino.sandboxxsample.model.mostpopular

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Media(val type: String,
                 @SerialName("media-metadata") val mediaMetadata: List<MediaMetadata>)
