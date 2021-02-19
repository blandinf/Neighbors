package com.blandinf.neighbors.adapters

import com.blandinf.neighbors.models.Neighbor

interface ListNeighborHandler {
    fun onDeleteNeibor(neighbor: Neighbor)
}