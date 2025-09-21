package `in`.surojit.seekhoassignmentapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import `in`.surojit.seekhoassignmentapp.ui.theme.AnimeAppTheme
import `in`.surojit.seekhoassignmentapp.ui.view.NavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimeAppTheme {
                NavGraph()
            }
        }
    }
}