package com.example.data.api.dto

data class UrlObjectList(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<UrlObject>
)