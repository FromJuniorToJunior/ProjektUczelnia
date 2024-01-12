package com.example.projektuczelnia.data


import com.google.gson.annotations.SerializedName

data class company(
    @SerializedName("metadata")
    val metadata: Metadata?,
    @SerializedName("record")
    val record: Record?
)