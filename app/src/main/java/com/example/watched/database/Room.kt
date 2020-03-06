package com.example.watched.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WatchedDao {
    @Query("select * from databaseshow")
    fun getShows(): LiveData<List<DatabaseShow>>
}

@Database(entities = [DatabaseShow::class], version = 1)
@TypeConverters(Converters::class)
abstract class WatchedDatabase : RoomDatabase() {
    abstract val dao: WatchedDao
}

private lateinit var INSTANCE: WatchedDatabase

fun getDatabase(context: Context): WatchedDatabase {
    synchronized(WatchedDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                WatchedDatabase::class.java,
                "watchedshows"
            ).build()
        }
    }
    return INSTANCE
}