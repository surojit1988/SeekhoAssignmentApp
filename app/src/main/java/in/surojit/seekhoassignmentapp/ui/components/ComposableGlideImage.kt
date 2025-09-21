package `in`.surojit.seekhoassignmentapp.ui.components

import android.widget.ImageView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


@Composable
fun ComposableGlideImage(url: String?, contentDescription: String?, modifier: Modifier = Modifier) {
    AndroidView(factory = { context ->
        ImageView(context).apply {
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
    }, modifier = modifier) { imageView ->
        Glide.with(imageView.context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
    }
}