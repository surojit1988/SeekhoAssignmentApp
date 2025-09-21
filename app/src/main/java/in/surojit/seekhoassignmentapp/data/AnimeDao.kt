package `in`.surojit.seekhoassignmentapp.data

import androidx.room.*
import `in`.surojit.seekhoassignmentapp.model.AnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {

    @Query("SELECT * FROM anime ORDER BY score DESC")
    fun getAllAnime(): Flow<List<AnimeEntity>>

    @Query("SELECT * FROM anime WHERE id = :id LIMIT 1")
    suspend fun getAnimeById(id: Int): AnimeEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<AnimeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(anime: AnimeEntity)

    @Query("DELETE FROM anime")
    suspend fun clearAll()
}