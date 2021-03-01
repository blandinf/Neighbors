package com.blandinf.neighbors.di

import android.app.Application
import com.blandinf.neighbors.repositories.NeighborRepository

object DI {
    lateinit var repository: NeighborRepository
    fun inject(application: Application) {
        repository = NeighborRepository.getInstance(application)
    }
}
