package ru.vtb.auth.domain.entity

data class User(
    val name: String,
    val email: String,
    val password: String
)