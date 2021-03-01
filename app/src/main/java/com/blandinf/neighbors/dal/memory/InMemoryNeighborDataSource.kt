package com.blandinf.neighbors.dal.memory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.blandinf.neighbors.dal.datasource.NeighborDatasource
import com.blandinf.neighbors.dal.fakeNeighbors
import com.blandinf.neighbors.models.Neighbor

class InMemoryNeighborDataSource() : NeighborDatasource {

    private val neighborsMutable = MutableLiveData<List<Neighbor>>()

    override val neighbors: LiveData<List<Neighbor>>
        get() = neighborsMutable

    init {
        neighborsMutable.value = fakeNeighbors
    }

    override fun deleteNeighbor(neighbor: Neighbor) {
        fakeNeighbors.remove(neighbor)
        neighborsMutable.value = fakeNeighbors
    }

    override fun createNeighbor(neighbor: Neighbor) {
        fakeNeighbors.add(neighbor)
        neighborsMutable.value = fakeNeighbors
    }

    override fun updateFavoriteStatus(favoriteStatus: Boolean, id: Long) {
        TODO("Not yet implemented")
    }

    override fun updateNeighbor(neighbor: Neighbor) {
        TODO("Not yet implemented")
    }
}
