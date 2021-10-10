package ru.vtb.stats.domain.entity

data class UserStatsSummary(
    val score: Int,
    val casesDone: Int,
    var lessonsDone: Int,
    val isQualified: Boolean,
    val money: Int,
)