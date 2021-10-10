package ru.vtb.stats.domain.entity

data class UserStatsRequest (
    val token: String,
    val score: Int,
    val casesDone: Int,
    val lessonsDone: Int,
    val isQualified: Boolean,
    val money: Int,
)