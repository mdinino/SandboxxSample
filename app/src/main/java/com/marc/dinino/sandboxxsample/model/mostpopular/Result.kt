package com.marc.dinino.sandboxxsample.model.mostpopular

import kotlinx.serialization.Serializable

@Serializable
data class Result(val url: String, val title: String, val media: List<Media>)