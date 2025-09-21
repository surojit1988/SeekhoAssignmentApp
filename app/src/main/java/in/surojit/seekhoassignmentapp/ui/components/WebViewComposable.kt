package `in`.surojit.seekhoassignmentapp.ui.components

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewComposable(url: String, modifier: Modifier = Modifier) {

    // Extract YouTube video ID
    val videoId = url.substringAfter("v=", url)
        .substringBefore("&") // handle extra params

    val embedUrl = "https://www.youtube.com/embed/$videoId?autoplay=1&modestbranding=1"

    val context = LocalContext.current

    AndroidView(factory = { context ->
        WebView(context).apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.loadWithOverviewMode = true
            settings.pluginState = WebSettings.PluginState.ON
            loadUrl(embedUrl)
        }
    }, modifier = modifier.clickable {
        val intent = Intent(Intent.ACTION_VIEW, embedUrl.toUri())
        context.startActivity(intent)}
    )
}