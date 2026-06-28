package com.ramazanm.model

import kotlinx.serialization.Serializable

@Serializable
data class Concept(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val soundUrl: String
) {
    constructor() : this("", """,""", "", "", "")
}