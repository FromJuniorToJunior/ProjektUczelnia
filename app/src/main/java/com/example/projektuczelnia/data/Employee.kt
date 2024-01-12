package com.example.projektuczelnia.data


import com.google.gson.annotations.SerializedName

data class Employee(
    @SerializedName("advanced")
    val advanced: Advanced,
    @SerializedName("name")
    val name: String,
    @SerializedName("surname")
    val surname: String
)