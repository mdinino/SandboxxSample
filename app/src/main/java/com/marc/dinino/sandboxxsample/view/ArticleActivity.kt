package com.marc.dinino.sandboxxsample.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.marc.dinino.sandboxxsample.view.theme.SandboxxSampleTheme
import com.marc.dinino.sandboxxsample.viewmodel.Article

class ArticleActivity : ComponentActivity() {

    companion object {
        private const val urlStringExtra = "EXTRA_URL"

        fun launch(context: Context, url: String) {
            val intent = Intent(context, ArticleActivity::class.java).apply {
                putExtra(urlStringExtra, url);
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SandboxxSampleTheme {
                Article.compose(getUrlExtra())
            }
        }
    }

    private fun getUrlExtra(): String {
        return intent.getStringExtra(urlStringExtra)!!
    }
}