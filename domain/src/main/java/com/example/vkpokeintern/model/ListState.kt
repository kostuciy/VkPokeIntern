package com.example.vkpokeintern.model

val EMPTY = ListState(
    0, null, null, emptyList(), null
)

data class ListState(
    val size: Int,
    val previous: String? = null,
    val next: String? = null,
    val list: List<UrlHolder>,
    val lastUnfilteredState: String? = null,
)
