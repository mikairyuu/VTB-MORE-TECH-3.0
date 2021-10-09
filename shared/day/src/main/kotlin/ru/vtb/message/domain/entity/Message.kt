package ru.vtb.message.domain.entity

data class Message(
    val type: Int,
    val imageId: Int,
    val textList: List<ListItem>?,
    val text: String,
    val buttons: List<ButtonItem>
)

data class ListItem(
    val text: String,
    val imageId: Int
)

data class ButtonItem(
    val id: Int,
    val text: String
)