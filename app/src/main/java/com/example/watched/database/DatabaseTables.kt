package com.example.watched.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity
data class DatabaseShow (
    @PrimaryKey
    val id: Int,
    val name: String?,
    val status: String?,
    val runtime: Int?,
    val premiered: String?,
    val officialSite: String?,
    val image: String?,
    val imageMedium: String?,
    val summary: String?,
    val seasons: List<Int>?
)

class Converters {
    @TypeConverter
    fun seasonsToString(value: List<Int>?): String? {
        return value?.joinToString()
    }

    @TypeConverter
    fun stringToSeason(value: String?): List<Int>? {
        return value?.let {
            it.split(", ").map { x -> x.toInt() }
        }
    }
}