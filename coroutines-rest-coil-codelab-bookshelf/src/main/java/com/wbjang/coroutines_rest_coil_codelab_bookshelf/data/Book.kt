package com.wbjang.coroutines_rest_coil_codelab_bookshelf.data

data class Book(
    val title: String,
    val authors: String,
    val imgSrc: String = "",
    val description: String = ""
)

