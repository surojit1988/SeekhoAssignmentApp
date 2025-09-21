package `in`.surojit.seekhoassignmentapp.model


data class AnimeDetailResponse(
    val data: AnimeDetail
)

data class AnimeDetail(
    val mal_id: Int,
    val title: String,
    val synopsis: String?,
    val episodes: Int?,
    val score: Double?,
    val images: Images,
    val trailer: Trailer?,
    val genres: List<Genre>,
    val characters: CharactersWrapper? // we'll fetch characters list if available via /full endpoint
)

data class Trailer(
    val youtube_id: String?,
    val url: String?
)

data class Genre(
    val name: String
)

data class CharactersWrapper(
    val data: List<CharacterEntry>?
)

data class CharacterEntry(
    val character: CharacterInner,
    val role: String? // "Main" or others
)

data class CharacterInner(
    val name: String,
    val images: Images?
)