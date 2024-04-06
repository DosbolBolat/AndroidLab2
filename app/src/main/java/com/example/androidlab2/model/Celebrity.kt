package com.example.androidlab2.model

import com.google.gson.annotations.SerializedName

data class Celebrity(
    val name: String,
    @SerializedName("net_worth") val netWorth: Long,
    val gender: String,
    @SerializedName("nationality") val nationality: String,
    @SerializedName("height") val heigth: Double,
    @SerializedName("max_height") val maxHeight: Double,
    @SerializedName("min_height") val minHeight: Double,
    val birthday: String,
)