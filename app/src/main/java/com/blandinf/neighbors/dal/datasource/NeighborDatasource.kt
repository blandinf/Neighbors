package com.blandinf.neighbors.dal.datasource

import androidx.lifecycle.LiveData
import com.blandinf.neighbors.models.Neighbor

interface NeighborDatasource {
    /**
     * Get all my Neighbors
     * @return [List]
     */
    val neighbors: LiveData<List<Neighbor>>

    /**
     * Deletes a neighbor
     * @param neighbor : Neighbor
     */
    fun deleteNeighbor(neighbor: Neighbor)

    /**
     * Create a neighbour
     * @param neighbor: Neighbor
     */
    fun createNeighbor(neighbor: Neighbor)

    /**
     * Update "Favorite status" of an existing Neighbour"
     */
    fun updateFavoriteStatus(favoriteStatus: Boolean, id: Long)

    /**
     * Update modified fields of an existing Neighbour"
     * @param neighbor: Neighbor
     */
    fun updateNeighbor(neighbor: Neighbor)
}
