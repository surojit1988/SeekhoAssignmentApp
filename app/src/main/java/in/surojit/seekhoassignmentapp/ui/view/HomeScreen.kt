package `in`.surojit.seekhoassignmentapp.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import `in`.surojit.seekhoassignmentapp.model.AnimeEntity
import `in`.surojit.seekhoassignmentapp.ui.components.ComposableGlideImage
import `in`.surojit.seekhoassignmentapp.viewmodel.HomeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel, onItemClick: (Int) -> Unit) {
    val animeList by viewModel.animeListFlow.collectAsState(initial = emptyList())

    Scaffold(topBar = {
        TopAppBar(title = { Text("Seekho Assignment App") }, actions = {
            IconButton(onClick = { viewModel.refresh() }) {
                Icon(Icons.Default.Refresh, contentDescription = "Refresh")
            }
        },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF1976D2), // Blue background
                titleContentColor = Color.White,   // Title text color
                actionIconContentColor = Color.White // Action icon color
            )
        )
    }) { padding ->
        if (animeList.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("No data available. Pull refresh or check network.")
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(padding)) {
                items(animeList) { anime ->
                    AnimeRow(anime = anime, onClick = { onItemClick(anime.id) })
                    HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
                }
            }
        }
    }
}

@Composable
fun AnimeRow(anime: AnimeEntity, onClick: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable { onClick() }) {
        ComposableGlideImage(url = anime.imageUrl, contentDescription = anime.title, modifier = Modifier.size(100.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(anime.title, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), maxLines = 2, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Episodes: ${anime.episodes ?: "?"}", style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.height(4.dp))
            Text("Rating: ${anime.score ?: "?"}", style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold))
        }
    }
}