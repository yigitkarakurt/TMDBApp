package com.yigitkarakurt.tmdbapp.model


import com.google.gson.annotations.SerializedName

data class SpokenLanguage(
    @SerializedName("english_name")
    val englishName: String?
)