package `in`.surojit.seekhoassignmentapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "anime")
data class AnimeEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val imageUrl: String,
    val episodes: Int?,
    val score: Double?,
    val synopsis: String? = null,
    val genres: String? = null,
    val characters: String? = null,
    val trailerUrl: String? = null,
    val lastUpdated: Long = System.currentTimeMillis()
)