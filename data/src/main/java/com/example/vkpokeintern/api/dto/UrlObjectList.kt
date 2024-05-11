package com.example.vkpokeintern.api.dto

data class UrlObjectList(
    val count: Int = 0,
    val next: String? = null,
    val previous: String? = null,
    val results: List<UrlObject>
)