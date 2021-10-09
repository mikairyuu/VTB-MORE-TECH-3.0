package ru.vtb.message.domain.entity

data class Header(
    val dayName: String,
    val imageId: Int,
    val text: Int,
    val buttons: List<ButtonItem>
)