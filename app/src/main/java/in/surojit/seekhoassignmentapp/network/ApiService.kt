package `in`.surojit.seekhoassignmentapp.network

import `in`.surojit.seekhoassignmentapp.model.AnimeDetailResponse
import `in`.surojit.seekhoassignmentapp.model.TopAnimeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("top/anime")
    suspend fun getTopAnime(
        @Query("page") page: Int = 1
    ): TopAnimeResponse

    @GET("anime/{id}/full")
    suspend fun getAnimeDetail(
        @Path("id") id: Int
    ): AnimeDetailResponse
}