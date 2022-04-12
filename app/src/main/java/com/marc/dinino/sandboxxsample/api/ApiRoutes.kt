package com.marc.dinino.sandboxxsample.api

object ApiRoutes {
    // TODO: take this out of the url
    private const val API_KEY = "7MfZ79G4lzDs2ql9uq73OOH1fwkHt5IR"

    private const val BASE_URL = "https://api.nytimes.com/svc"
    const val MOST_VIEWED_LAST_DAY = "$BASE_URL/mostpopular/v2/viewed/1.json?api-key=$API_KEY"
}