package com.example.vkpokeintern.viewmodel

import androidx.lifecycle.ViewModel
import com.example.vkpokeintern.model.ListState
import com.example.vkpokeintern.model.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    // usecases TODO()
): ViewModel() {
//    val pokemonListState: Flow<ListState<TODO()>> = TODO()
//    val abilitiesListState:
//    val typeListState:
//    val locationListState:

    val listState: Flow<ListState> = TODO() // from repo

    private var _currentListState: ListState? = ListState()
    val currentListState: ListState?
        get() = _currentListState

    var selectionListState: ListState? = null

    private val _viewState: MutableStateFlow<ViewState> = TODO()
    val viewState = _viewState.asStateFlow()

//    fun provideSelection() = TODO()

}