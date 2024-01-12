package com.example.projektuczelnia.data


import com.google.gson.annotations.SerializedName

data class Record(
    @SerializedName("employees")
    val employees: List<Employee>,
    @SerializedName("name")
    val name: String,
    @SerializedName("owner")
    val owner: String,
    @SerializedName("seniority")
    val seniority: Int,
    @SerializedName("image")
    var image: String
)