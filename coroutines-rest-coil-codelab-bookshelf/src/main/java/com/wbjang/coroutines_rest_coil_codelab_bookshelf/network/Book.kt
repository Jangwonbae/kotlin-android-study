package com.wbjang.coroutines_rest_coil_codelab_bookshelf.network

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class BookResponse(
    val items: List<BookItem>? = null
)

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class BookItem(
    val volumeInfo: VolumeInfo
)

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class VolumeInfo(
    val title: String,
    val authors: List<String>? = null,
    val description: String? = null,
    val imageLinks: ImageLinks? = null
)

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class ImageLinks(
    val thumbnail: String
)