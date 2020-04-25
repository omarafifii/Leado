package com.leado.model

data class User(
    val id: String, val name: String, val image: String, val points: Int,
    val Rank: Int, val badges: List<Badge>, val lessons: Map<String, Int>
) {
}