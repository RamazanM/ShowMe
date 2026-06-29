package com.ramazanm.model

import kotlinx.serialization.Serializable

@Serializable
data class Concept(
    val id: String? = null,
    val title: String,
    val description: String,
    val imageUrls: List<String>,
    val soundUrl: String
) {
    constructor() : this("", """,""", "", listOf(""), "")
}