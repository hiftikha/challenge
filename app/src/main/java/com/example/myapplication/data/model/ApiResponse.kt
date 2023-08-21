package com.example.myapplication.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class ApiResponse<T>(val info: Info, val results: T)

data class Info(val count: Int, val pages: Int, val next: String, val prev: String?)

@Parcelize
data class NameUrlObj(val name: String, val url: String): Parcelable

@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val location: NameUrlObj,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
): Parcelable

@Parcelize
data class Episode(
    val id: Int,
    val name: String,
    @SerializedName("air_date")
    val airDate: String,
    val episode: String,
    val characters: List<String>,
    val created: String
): Parcelable
