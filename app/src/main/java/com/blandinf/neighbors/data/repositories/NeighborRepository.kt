package com.blandinf.neighbors.data.repositories

import com.blandinf.neighbors.data.datasource.NeighborDatasource
import com.blandinf.neighbors.data.service.InMemoryNeighborDataSource
import com.blandinf.neighbors.models.Neighbor

class NeighborRepository {
    private val dataSource: NeighborDatasource

    init {
        dataSource = InMemoryNeighborDataSource()
    }

    // Méthode qui retourne la liste des voisins
    fun getNeighbours(): List<Neighbor> = dataSource.neighbours

    fun deleteNeighbor(neighbor: Neighbor) = dataSource.deleteNeighbour(neighbor)

    fun createNeighbor(neighbor: Neighbor) = dataSource.createNeighbour(neighbor)

    companion object {
        private var instance: NeighborRepository? = null

        // On crée un méthode qui retourne l'instance courante du repository si elle existe ou en crée une nouvelle sinon
        fun getInstance(): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository()
            }
            return instance!!
        }
    }
}