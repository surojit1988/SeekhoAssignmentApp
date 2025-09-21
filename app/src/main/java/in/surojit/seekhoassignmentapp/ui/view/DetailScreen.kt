package `in`.surojit.seekhoassignmentapp.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import `in`.surojit.seekhoassignmentapp.ui.components.ComposableGlideImage
import `in`.surojit.seekhoassignmentapp.ui.components.TrailerPlayer
import `in`.surojit.seekhoassignmentapp.ui.components.WebViewComposable
import `in`.surojit.seekhoassignmentapp.viewmodel.DetailViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(viewModel: DetailViewModel, animeId: Int, onBack: () -> Unit) {
    val uiState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(animeId) {
        viewModel.loadDetail(animeId)
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text(uiState.title.ifBlank { "Detail" }) }, navigationIcon = {
            IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back") }
        },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF1976D2), // Blue background
                titleContentColor = Color.White,   // Title text color
                actionIconContentColor = Color.White // Action icon color
            )
        )
    }) { padding ->
        if (uiState.loading) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .verticalScroll(rememberScrollState())
                .padding(padding)) {

                // Try to find a YouTube url by querying the details endpoint directly (quick approach)
                // For simplicity, check if synopsis available -> show image/trailer.
                // In this example, we'll attempt to construct a youtube watch link if youtube_id present
                // (You may modify repository to return trailer full url).
                if (!uiState.trailerUrl.isNullOrBlank()) {
                    // Show video player if trailer URL available
                    WebViewComposable(
                        url = uiState.trailerUrl!!,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(450.dp)
                    )
                } else if (uiState.imageUrl.isNotBlank()) {
                    // Fallback to poster image
                    ComposableGlideImage(
                        url = uiState.imageUrl,
                        contentDescription = uiState.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(450.dp)
                    )
                }
//                if (uiState.imageUrl.isNotBlank()) {
//                    // Show image first (if trailer not available)
//                    ComposableGlideImage(url = uiState.imageUrl, contentDescription = uiState.title, modifier = Modifier
//                        .fillMaxWidth()
//                        .height(450.dp))
//                }

                Spacer(modifier = Modifier.height(18.dp))
                Text(uiState.title, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
                Spacer(modifier = Modifier.height(6.dp))
                Text("Episodes: ${uiState.episodes ?: "?"}   Rating: ${uiState.score ?: "?"}", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
                Spacer(modifier = Modifier.height(8.dp))
                Text("Genres: ${uiState.genres ?: "Unknown"}", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
                Spacer(modifier = Modifier.height(8.dp))
                if (uiState.characters.isNullOrBlank()){
                    Text("Main Cast: Unknown", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
                }else{
                    Text("Main Cast: ${uiState.characters}", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text("Synopsis", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
                Spacer(modifier = Modifier.height(6.dp))
                Text(uiState.synopsis ?: "No synopsis available")

                Spacer(modifier = Modifier.height(12.dp))
                // If you want to embed a YouTube video, one approach: pass the YouTube watch URL to WebViewComposable
                // To find trailer url you could extend repository to return trailer.youtube_id
            }
        }
    }
}