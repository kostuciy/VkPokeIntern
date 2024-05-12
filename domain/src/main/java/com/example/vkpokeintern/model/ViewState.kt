package com.example.vkpokeintern.model

data class ViewState(
    val state: ViewStateTypes = ViewStateTypes.LOADING
) {

    enum class ViewStateTypes {
        LIST, LOADING, ERROR
    }
}