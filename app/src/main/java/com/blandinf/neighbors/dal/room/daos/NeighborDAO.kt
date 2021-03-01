package com.blandinf.neighbors.dal.room.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.blandinf.neighbors.dal.room.entities.NeighborEntity

@Dao
interface NeighborDao {
    @Query("SELECT * from neighbors")
    fun getNeighbors(): LiveData<List<NeighborEntity>>
    @Insert
    fun add(entity: NeighborEntity)
    @Update
    fun update(entity: NeighborEntity)
    @Query("UPDATE neighbors SET favorite = :favoriteStatus WHERE id = :neighborId")
    fun updateFavoriteStatus(favoriteStatus: Boolean, neighborId: Long)
    @Delete
    fun delete(entity: NeighborEntity)
}
