package `in`.surojit.seekhoassignmentapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import `in`.surojit.seekhoassignmentapp.data.AnimeDatabase
import `in`.surojit.seekhoassignmentapp.repository.AnimeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel (application: Application) : AndroidViewModel(application) {

    private val repo =
        AnimeRepository(AnimeDatabase.getDatabase(application).animeDao(), application)

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState

    fun loadDetail(id: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true, animeId = id)
            val entity = repo.getAnimeDetail(id)
            entity?.let {
                // Try to discover trailer via Jikan detail endpoint again (repository inserted more fields earlier)
                // For trailer we can attempt a second retrofit call; already done in repo.getAnimeDetail
                _uiState.value = DetailUiState(
                    loading = false,
                    animeId = it.id,
                    title = it.title,
                    synopsis = it.synopsis,
                    imageUrl = it.imageUrl,
                    episodes = it.episodes,
                    score = it.score,
                    genres = it.genres,
                    characters = it.characters,
                    trailerUrl = it.trailerUrl // we'll build trailer URL from separate call if needed elsewhere
                )
            } ?: run {
                _uiState.value = _uiState.value.copy(loading = false)
            }
        }
    }
}

data class DetailUiState(
    val loading: Boolean = true,
    val animeId: Int = 0,
    val title: String = "",
    val synopsis: String? = null,
    val imageUrl: String = "",
    val episodes: Int? = null,
    val score: Double? = null,
    val genres: String? = null,
    val characters: String? = null,
    val trailerUrl: String? = null // youtube full url if available
)