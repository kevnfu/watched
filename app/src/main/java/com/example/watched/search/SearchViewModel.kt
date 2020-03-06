package com.example.watched.search

import android.app.Application
import androidx.lifecycle.*
import com.example.watched.database.getDatabase
import com.example.watched.network.SearchResult
import com.example.watched.network.Network
import com.example.watched.repository.Repository
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: Repository) : ViewModel() {
    val searchQuery = MutableLiveData<String>()

//    private val _searchResults = MutableLiveData<List<SearchResult>>()
//    val searchResult = Transformations.switchMap(searchQuery) {
//        it?.let {
//            val y = MutableLiveData<List<SearchResult>>()
//            viewModelScope.launch {
//                y.value = TvMazeApi.service.searchShows(it)
//            }
//            return@let y
//        }
//    }
//
//    val singleShow = Transformations.map(searchResult) {
//        it?.let{
//           it.firstOrNull()?.show?.name
//        }
//    }

    val searchResult = repository.searchResult

    fun executeSearch() {
        viewModelScope.launch {
            searchQuery.value?.let {
                repository.searchShows(it)
            }
        }
    }

    class Factory(private val application: Application) :
        ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(SearchViewModel::class.java)) {
                return SearchViewModel(Repository(getDatabase(application))) as T
            }
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}