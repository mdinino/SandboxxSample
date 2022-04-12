package com.marc.dinino.sandboxxsample.model.mostpopular

import kotlinx.serialization.Serializable

@Serializable
data class Response(val status: String, val results: List<Result>)

