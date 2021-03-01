package com.blandinf.neighbors.dal.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.blandinf.neighbors.dal.fakeNeighbors
import com.blandinf.neighbors.dal.mappers.toEntity
import com.blandinf.neighbors.dal.room.daos.NeighborDao
import com.blandinf.neighbors.dal.room.entities.NeighborEntity
import java.util.concurrent.Executors

@Database(entities = [NeighborEntity::class], version = 1)
abstract class NeighborDatabase : RoomDatabase() {
    abstract fun neighborDao(): NeighborDao

    companion object {
        private var instance: NeighborDatabase? = null

        fun getDataBase(application: Application): NeighborDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    application.applicationContext,
                    NeighborDatabase::class.java,
                    "neighbor_database.db"
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        insertFakeData()
                    }
                })
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }

        private fun insertFakeData() {
            Executors.newSingleThreadExecutor().execute {
                fakeNeighbors.forEach {
                    instance?.neighborDao()?.add(it.toEntity())
                }
            }
        }
    }
}
