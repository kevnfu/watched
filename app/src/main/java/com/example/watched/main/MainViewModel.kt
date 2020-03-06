package com.example.watched.main

import android.util.Log
import androidx.lifecycle.*
import com.example.watched.network.NetworkShow
import com.example.watched.network.Network
import com.example.watched.network.SearchResult
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    private val _navigateToSearch = MutableLiveData<Boolean>()
    val navigateToSearch: LiveData<Boolean>
        get() = _navigateToSearch

    private val _data = MutableLiveData<NetworkShow>()
    val data: LiveData<NetworkShow>
        get() = _data

    val dataText = Transformations.map(data) {
        it.toString()
    }

    fun getData() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                val show: List<SearchResult>
                Log.i("watched", "about to search network")
                show = Network.tvMaze.searchShows("brooklyn")
                Log.i("watched", "result from network")
                _data.value = show.firstOrNull()?.show
            }
        }
    }

    fun navigateToSearchFragment() {
        _navigateToSearch.value = true
    }

    fun navigateToSearchComplete() {
        _navigateToSearch.value = false
    }

}