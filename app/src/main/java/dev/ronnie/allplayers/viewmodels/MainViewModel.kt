package dev.ronnie.allplayers.viewmodels

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dev.ronnie.allplayers.data.PlayersRepository
import dev.ronnie.allplayers.models.Player
import kotlinx.coroutines.flow.Flow

class MainViewModel @ViewModelInject constructor(
    private val repository: PlayersRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var currentResult: Flow<PagingData<Player>>? = null

    fun searchPlayers(): Flow<PagingData<Player>> {
        val newResult: Flow<PagingData<Player>> =
            repository.getPlayers().cachedIn(viewModelScope)
        currentResult = newResult
        return newResult
    }

}