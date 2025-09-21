package `in`.surojit.seekhoassignmentapp.model


data class TopAnimeResponse(
    val data: List<TopAnimeEntry>
)

data class TopAnimeEntry(
    val mal_id: Int,
    val title: String,
    val images: Images,
    val episodes: Int?,
    val score: Double?
)

data class Images(
    val jpg: ImageJpg
)

data class ImageJpg(
    val image_url: String
)