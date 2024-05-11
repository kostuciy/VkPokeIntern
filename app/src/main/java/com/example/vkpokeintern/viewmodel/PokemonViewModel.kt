package com.example.vkpokeintern.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkpokeintern.model.EMPTY
import com.example.vkpokeintern.model.ListState
import com.example.vkpokeintern.model.ModelType
import com.example.vkpokeintern.model.ViewState
import com.example.vkpokeintern.usecase.GetAbilityListStateUseCase
import com.example.vkpokeintern.usecase.GetAbilityListUseCase
import com.example.vkpokeintern.usecase.GetLocationListStateUseCase
import com.example.vkpokeintern.usecase.GetLocationListUseCase
import com.example.vkpokeintern.usecase.GetPokemonListStateUseCase
import com.example.vkpokeintern.usecase.GetPokemonListUseCase
import com.example.vkpokeintern.usecase.GetPokemonUseCase
import com.example.vkpokeintern.usecase.GetTypesListStateUseCase
import com.example.vkpokeintern.usecase.GetTypesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    val getPokemonListUseCase: GetPokemonListUseCase,
    val getAbilityListUseCase: GetAbilityListUseCase,
    val getTypesListUseCase: GetTypesListUseCase,
    val getLocationListUseCase: GetLocationListUseCase,
    val getPokemonListStateUseCase: GetPokemonListStateUseCase,
    val getAbilityListStateUseCase: GetAbilityListStateUseCase,
    val getTypesListStateUseCase: GetTypesListStateUseCase,
    val getLocationListStateUseCase: GetLocationListStateUseCase,
    val getPokemonUseCase: GetPokemonUseCase


    // usecases TODO()
): ViewModel() {
//    val pokemonListState: Flow<ListState<TODO()>> = TODO()
//    val abilitiesListState:
//    val typeListState:
//    val locationListState:

//    val listState: Flow<ListState> = TODO() // from repo

//    private var _currentListState: ListState? = ListState()
//    val currentListState: ListState?
//        get() = _currentListState
//
//    var selectionListState: ListState? = null

    val pokemonList = getPokemonListUseCase.execute()
    val abilityList = getAbilityListUseCase.execute()
    val locationList = getLocationListUseCase.execute()
    val typesListUseCase = getTypesListUseCase.execute()

    private val _pokemonListState: MutableStateFlow<ListState> = MutableStateFlow(EMPTY)
    val pokemonListState = _pokemonListState.asStateFlow()
    private val _abilityListState: MutableStateFlow<ListState> = MutableStateFlow(EMPTY)
    val abilityListState = _abilityListState.asStateFlow()
    private val _typeListState: MutableStateFlow<ListState> = MutableStateFlow(EMPTY)
    val typeListState = _typeListState.asStateFlow()
    private val _locationListState: MutableStateFlow<ListState> = MutableStateFlow(EMPTY)
    val locationListState = _locationListState.asStateFlow()

    private val _viewState: MutableStateFlow<ViewState> =
        MutableStateFlow(ViewState())
    val viewState = _viewState.asStateFlow()

//    page update -> pokemon info -> pokemon list
    init {
        ModelType.entries.forEach {
            changePage(it)
        }
    }

    fun changePage(
        listType: ModelType,
        operation: PageOperationType = PageOperationType.RESET
    ) {
        viewModelScope.launch {
//            try { TODO: remove comm
                _viewState.value = ViewState(ViewState.ViewStateTypes.LOADING)

                val listState = when (listType) {
                    ModelType.POKEMON -> _pokemonListState.value
                    ModelType.ABILITY -> _abilityListState.value
                    ModelType.LOCATION -> _locationListState.value
                    ModelType.TYPE -> _typeListState.value
                }
                val url = when (operation) {
                    PageOperationType.NEXT -> listState.next ?: return@launch
                    PageOperationType.BACK -> listState.previous ?: return@launch
                    PageOperationType.RESET -> null
                }
                when (listType) {
                    ModelType.POKEMON ->
                        _pokemonListState.value = getPokemonListStateUseCase.execute(url)
                    ModelType.ABILITY ->
                        _abilityListState.value = getAbilityListStateUseCase.execute(url)
                    ModelType.TYPE ->
                        _typeListState.value = getTypesListStateUseCase.execute(url)
                    ModelType.LOCATION ->
                        _locationListState.value = getLocationListStateUseCase.execute(url)
                }

                loadModels(listType)

                _viewState.value = ViewState(ViewState.ViewStateTypes.LIST)
//            } catch (e: Exception) {
//                _viewState.value = ViewState(ViewState.ViewStateTypes.ERROR)
//            }
        }
    }

    private suspend fun removeModels(modelType: ModelType) {
        // TODO()
    }

    private suspend fun loadModels(modelType: ModelType) {
        when (modelType) {
                ModelType.POKEMON -> {
                    val pokemonHolders = pokemonListState.value.list
                    Log.d("repository_tag", "${pokemonHolders}")
                    withContext(Dispatchers.IO) {
                        pokemonHolders.forEach {
                            Log.d("repository_tag", "${it.url}")
                            getPokemonUseCase.execute(it.url)
                        }
                    }
                }
                ModelType.ABILITY -> {
                    val abilityHolders = abilityListState.value.list
                    withContext(Dispatchers.IO) {
//                        abilityHolders.forEach { getAbilityUseCase.execute(it.url) }
//                        TODO()
                    }
                }
                ModelType.TYPE -> {
                    val typeHolders = typeListState.value.list
                    withContext(Dispatchers.IO) {
//                        typeHolders.forEach { getTypeUseCase.execute(it.url) }
//                        TODO()
                    }
                }
                ModelType.LOCATION -> {
                    val locationHolders = locationListState.value.list
                    withContext(Dispatchers.IO) {
//                        locationHolders.forEach { getLocationUseCase.execute(it.url) }
//                        TODO()
                    }
                }
            }
    }

//    fun provideSelection() = TODO()

    enum class PageOperationType {
        NEXT, BACK, RESET
    }
}