package com.example.vkpokeintern.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkpokeintern.model.EMPTY
import com.example.vkpokeintern.model.ListState
import com.example.vkpokeintern.model.ModelType
import com.example.vkpokeintern.model.ViewState
import com.example.vkpokeintern.usecase.ClearAbilitiesUseCase
import com.example.vkpokeintern.usecase.ClearLocationsUseCase
import com.example.vkpokeintern.usecase.ClearPokemonsUseCase
import com.example.vkpokeintern.usecase.ClearTypesUseCase
import com.example.vkpokeintern.usecase.GetAbilityListStateUseCase
import com.example.vkpokeintern.usecase.GetAbilityListUseCase
import com.example.vkpokeintern.usecase.GetAbilityUseCase
import com.example.vkpokeintern.usecase.GetLocationListStateUseCase
import com.example.vkpokeintern.usecase.GetLocationListUseCase
import com.example.vkpokeintern.usecase.GetLocationUseCase
import com.example.vkpokeintern.usecase.GetPokemonListStateUseCase
import com.example.vkpokeintern.usecase.GetPokemonListUseCase
import com.example.vkpokeintern.usecase.GetPokemonUseCase
import com.example.vkpokeintern.usecase.GetTypeUseCase
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
    val getPokemonUseCase: GetPokemonUseCase,
    val getAbilityUseCase: GetAbilityUseCase,
    val getLocationUseCase: GetLocationUseCase,
    val getTypeUseCase: GetTypeUseCase,
    val clearTypesUseCase: ClearTypesUseCase,
    val clearPokemonsUseCase: ClearPokemonsUseCase,
    val clearAbilitiesUseCase: ClearAbilitiesUseCase,
    val clearLocationUseCase: ClearLocationsUseCase


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

    fun nextPage(listType: ModelType, startFilteringPage: Boolean = false) =
        changePage(listType, PageOperationType.NEXT, startFilteringPage)

    fun previousPage(listType: ModelType, startFilteringPage: Boolean = false) =
        changePage(listType, PageOperationType.BACK, startFilteringPage)

    private fun resetPage(listType: ModelType, startFilteringPage: Boolean = false) =
        changePage(listType, PageOperationType.RESET, startFilteringPage)

    fun exitPage(listType: ModelType) {
        val listState = when (listType) {
            ModelType.POKEMON -> _pokemonListState
            ModelType.ABILITY -> _abilityListState
            ModelType.TYPE -> _typeListState
            ModelType.LOCATION -> _locationListState
        }

        if (listState.value.lastUnfilteredState != null) {
            resetPage(listType)
        }
    }

    fun reload(listType: ModelType) {
        viewModelScope.launch {
            try {
                _viewState.value = ViewState(ViewState.ViewStateTypes.LOADING)
                loadModels(listType)
                _viewState.value = ViewState(ViewState.ViewStateTypes.LIST)
            } catch(e: Exception) {
                _viewState.value = ViewState(ViewState.ViewStateTypes.ERROR)
            }
        }
    }

    private fun changePage(
        listType: ModelType,
        operation: PageOperationType = PageOperationType.RESET,
        startFilteringPage: Boolean = false
    ) {
        viewModelScope.launch {
            try {
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
                    PageOperationType.RESET -> listState.lastUnfilteredState
                }

                getNewState(
                    listType,
                    startFilteringPage,
                    operation == PageOperationType.RESET,
                    url
                )
                loadModels(listType)

                _viewState.value = ViewState(ViewState.ViewStateTypes.LIST)
            } catch (e: Exception) {
                _viewState.value = ViewState(ViewState.ViewStateTypes.ERROR)
            }
        }
    }

    private suspend fun getNewState(
        listType: ModelType,
        startFilteringPage: Boolean,
        isResetting: Boolean,
        url: String?,
    ) {
        when (listType) {
            ModelType.POKEMON ->
                _pokemonListState.value = getPokemonListStateUseCase.execute(url).copy(
                    lastUnfilteredState = getNewLastUnfiltered(
                        startFilteringPage,
                        _pokemonListState.value.lastUnfilteredState,
                        url, isResetting
                    )
                )
            ModelType.ABILITY ->
                _abilityListState.value = getAbilityListStateUseCase.execute(url).copy(
                    lastUnfilteredState = getNewLastUnfiltered(
                        startFilteringPage,
                        _abilityListState.value.lastUnfilteredState,
                        url, isResetting
                    )
                )
            ModelType.TYPE ->
                _typeListState.value = getTypesListStateUseCase.execute(url).copy(
                    lastUnfilteredState = getNewLastUnfiltered(
                        startFilteringPage,
                        _typeListState.value.lastUnfilteredState,
                        url, isResetting
                    )
                )
            ModelType.LOCATION ->
                _locationListState.value = getLocationListStateUseCase.execute(url).copy(
                    lastUnfilteredState = getNewLastUnfiltered(
                        startFilteringPage,
                        _locationListState.value.lastUnfilteredState,
                        url, isResetting
                    )
                )
        }
    }

    private fun getNewLastUnfiltered(
        startFilteringPage: Boolean,
        lastUnfiltered: String?,
        newUrl: String?,
        isResetting: Boolean
    ): String? =
        when {
            isResetting || newUrl == null -> null
            startFilteringPage -> newUrl
            lastUnfiltered != null -> lastUnfiltered
            else -> null
        }


    private suspend fun loadModels(modelType: ModelType) {
        when (modelType) {
                ModelType.POKEMON -> {
                    val pokemonHolders = pokemonListState.value.list
                    Log.d("repository_tag", "${pokemonHolders}")
                    clearPokemonsUseCase.execute()
                    getPokemonUseCase.execute(pokemonHolders.map { it.url })
                }
                ModelType.ABILITY -> {
                    val abilityHolders = abilityListState.value.list
                    withContext(Dispatchers.IO) {
                        clearAbilitiesUseCase.execute()
                        abilityHolders.forEach { getAbilityUseCase.execute(it.url) }
                    }
                }
                ModelType.TYPE -> {
                    val typeHolders = typeListState.value.list
                    withContext(Dispatchers.IO) {
                        clearTypesUseCase.execute()
                        typeHolders.forEach { getTypeUseCase.execute(it.url) }
                    }
                }
                ModelType.LOCATION -> {
                    val locationHolders = locationListState.value.list
                    withContext(Dispatchers.IO) {
                        clearLocationUseCase.execute()
                        locationHolders.forEach { getLocationUseCase.execute(it.url) }
                    }
                }
            }
    }

//    fun provideSelection() = TODO()

    enum class PageOperationType {
        NEXT, BACK, RESET
    }
}