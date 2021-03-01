package com.blandinf.neighbors.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.blandinf.neighbors.dal.datasource.NeighborDatasource
import com.blandinf.neighbors.dal.datasource.RoomNeighborDatasource
import com.blandinf.neighbors.dal.fakeNeighbors
import com.blandinf.neighbors.models.Neighbor

class NeighborRepository private constructor(application: Application) {
    private val dataSource: NeighborDatasource

    init {
        dataSource = RoomNeighborDatasource(application = application)
    }

    fun getNeighbors(): LiveData<List<Neighbor>> = dataSource.neighbors

    fun getFakesNeighbors(): List<Neighbor> = fakeNeighbors

    fun deleteNeighbor(neighbor: Neighbor) = dataSource.deleteNeighbor(neighbor)

    fun createNeighbor(neighbor: Neighbor) = dataSource.createNeighbor(neighbor)

    fun updateNeighborFavoriteStatus(favoriteStatus: Boolean, id: Long) = dataSource.updateFavoriteStatus(favoriteStatus, id)

    companion object {
        private var instance: NeighborRepository? = null

        fun getInstance(application: Application): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository(application)
            }
            return instance!!
        }
    }
}
