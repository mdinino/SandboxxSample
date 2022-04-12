package com.marc.dinino.sandboxxsample.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.marc.dinino.sandboxxsample.view.theme.SandboxxSampleTheme
import com.marc.dinino.sandboxxsample.viewmodel.Newsfeed

class NewsfeedActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SandboxxSampleTheme {
                Newsfeed.compose(this)
            }
        }
    }
}