package com.blandinf.neighbors.dal.datasource

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.blandinf.neighbors.dal.mappers.toEntity
import com.blandinf.neighbors.dal.mappers.toNeighbor
import com.blandinf.neighbors.dal.room.NeighborDatabase
import com.blandinf.neighbors.dal.room.daos.NeighborDao
import com.blandinf.neighbors.models.Neighbor

class RoomNeighborDatasource(application: Application) : NeighborDatasource {
    private val database: NeighborDatabase = NeighborDatabase.getDataBase(application)
    private val dao: NeighborDao = database.neighborDao()

    private val _neighbors = MediatorLiveData<List<Neighbor>>()

    init {
        _neighbors.addSource(dao.getNeighbors()) { entities ->
            _neighbors.value = entities.map { entity ->
                entity.toNeighbor()
            }
        }
    }

    override val neighbors: LiveData<List<Neighbor>>
        get() = _neighbors

    override fun deleteNeighbor(neighbor: Neighbor) {
        dao.delete(neighbor.toEntity())
    }

    override fun createNeighbor(neighbor: Neighbor) {
        dao.add(neighbor.toEntity())
    }

    override fun updateFavoriteStatus(favoriteStatus: Boolean, id: Long) {
        dao.updateFavoriteStatus(favoriteStatus, id)
    }

    override fun updateNeighbor(neighbor: Neighbor) {
        dao.update(neighbor.toEntity())
    }
}
