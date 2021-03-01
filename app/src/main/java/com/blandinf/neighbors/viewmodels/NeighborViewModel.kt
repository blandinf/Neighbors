package com.blandinf.neighbors.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.blandinf.neighbors.di.DI
import com.blandinf.neighbors.models.Neighbor
import com.blandinf.neighbors.repositories.NeighborRepository
import java.util.concurrent.Executors

class NeighborViewModel : ViewModel() {
    private val repository: NeighborRepository = DI.repository

    fun getNeighbors(): LiveData<List<Neighbor>> {
        return repository.getNeighbors()
    }

    fun createNeighbor(neighbor: Neighbor) {
        Executors.newSingleThreadExecutor().execute {
            repository.createNeighbor(neighbor)
        }
    }

    fun deleteNeighbor(neighbor: Neighbor) {
        Executors.newSingleThreadExecutor().execute {
            repository.deleteNeighbor(neighbor)
        }
    }

    fun updateNeighborFavoriteStatus(favoriteStatus: Boolean, id: Long) {
        Executors.newSingleThreadExecutor().execute {
            repository.updateNeighborFavoriteStatus(favoriteStatus, id)
        }
    }
}
