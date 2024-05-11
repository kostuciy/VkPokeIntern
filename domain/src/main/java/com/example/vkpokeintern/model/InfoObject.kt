package com.example.vkpokeintern.model

data class InfoObject(
    val count: Int = 0,
    val next: String? = null,
    val previous: String? = null,
    val results: List<UrlHolder>
)

data class UrlHolder(
    val name: String,
    val url: String,
)
