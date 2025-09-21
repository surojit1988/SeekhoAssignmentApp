package `in`.surojit.seekhoassignmentapp.repository

import android.content.Context
import `in`.surojit.seekhoassignmentapp.data.AnimeDao
import `in`.surojit.seekhoassignmentapp.model.AnimeEntity
import `in`.surojit.seekhoassignmentapp.model.TopAnimeEntry
import `in`.surojit.seekhoassignmentapp.network.RetrofitClient
import `in`.surojit.seekhoassignmentapp.ui.components.NetworkUtils
import kotlinx.coroutines.flow.Flow


class AnimeRepository (private val dao: AnimeDao, private val context: Context) {

    val cachedAnimeFlow: Flow<List<AnimeEntity>> = dao.getAllAnime()

    private val service = RetrofitClient.service

    suspend fun refreshTopAnime(page: Int = 1) {
        if (!NetworkUtils.isOnline(context)) return
        val response = service.getTopAnime(page)
        val entities = response.data.map { entryToEntity(it) }
        dao.insertAll(entities)
    }

    suspend fun getAnimeDetail(id: Int): AnimeEntity? {
        // Check local first
        val local = dao.getAnimeById(id)
        if (local != null && local.synopsis != null) return local

        if (!NetworkUtils.isOnline(context)) return local

        val res = service.getAnimeDetail(id)
        val d = res.data
        val genres = d.genres.joinToString(", ") { it.name }
        val characters = d.characters?.data?.take(5)?.joinToString(", ") { it.character.name } ?: ""
        val entity = AnimeEntity(
            id = d.mal_id,
            title = d.title,
            imageUrl = d.images.jpg.image_url,
            episodes = d.episodes,
            score = d.score,
            synopsis = d.synopsis,
            genres = genres,
            characters = characters,
            trailerUrl = d.trailer?.url,
            lastUpdated = System.currentTimeMillis()
        )
        dao.insert(entity)
        return entity
    }

    private fun entryToEntity(it: TopAnimeEntry): AnimeEntity {
        return AnimeEntity(
            id = it.mal_id,
            title = it.title,
            imageUrl = it.images.jpg.image_url,
            episodes = it.episodes,
            score = it.score,
            synopsis = null,
            genres = null,
            characters = null,
            trailerUrl = null
        )
    }
}