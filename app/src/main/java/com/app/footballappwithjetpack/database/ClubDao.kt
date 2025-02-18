package com.app.footballappwithjetpack.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.footballappwithjetpack.model.Club

@Dao
interface ClubDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertClub(club: Club)

    @Delete
    fun deleteClub(club: Club)

    @Query("SELECT * FROM club ORDER BY id ASC")
    fun getFavoriteClub(): LiveData<List<Club>>

    @Query("SELECT EXISTS(SELECT 1 FROM club WHERE id = :id)")
    fun isFavorite(id: Int): LiveData<Boolean>
}