package com.example.watched.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.watched.database.WatchedDatabase
import com.example.watched.domain.Show
import com.example.watched.network.Network
import com.example.watched.network.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val database: WatchedDatabase) {
    private val _searchResult = MutableLiveData<List<Show>?>()
    val searchResult: LiveData<List<Show>?>
        get() = _searchResult

    suspend fun searchShows(word: String) {
        withContext(Dispatchers.IO) {
            val response = Network.tvMaze.searchShows(word)
            _searchResult.value = response.map { it -> it.show }.asDomainModel()
        }
    }
}