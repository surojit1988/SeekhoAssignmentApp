package `in`.surojit.seekhoassignmentapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import `in`.surojit.seekhoassignmentapp.data.AnimeDatabase
import `in`.surojit.seekhoassignmentapp.repository.AnimeRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class HomeViewModel (application: Application) : AndroidViewModel(application) {

    private val repo =
        AnimeRepository(AnimeDatabase.getDatabase(application).animeDao(), application)
    val animeListFlow = repo.cachedAnimeFlow
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    init {
        // Try refresh on startup
        viewModelScope.launch {
            repo.refreshTopAnime()
        }
    }

    fun refresh() {
        viewModelScope.launch {
            repo.refreshTopAnime()
        }
    }
}