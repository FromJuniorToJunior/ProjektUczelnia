package com.example.projektuczelnia.data


import com.google.gson.annotations.SerializedName

data class Advanced(
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("age")
    val age: Int,
    @SerializedName("father_name")
    val fatherName: String,
    @SerializedName("hire_date")
    val hireDate: String,
    @SerializedName("img")
    val img: String,
    @SerializedName("like_bananas")
    val likeBananas: Boolean,
    @SerializedName("retired")
    val retired: Boolean,
    @SerializedName("seniority")
    val seniority: Int
)