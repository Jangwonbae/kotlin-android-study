package com.wbjang.coroutines_rest_coil_codelab_practice.network

import android.annotation.SuppressLint
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class Amphibian(
    val name : String,
    val type : String,
    val description : String,
    @SerialName(value = "img_src")
    val imgSrc : String,
)