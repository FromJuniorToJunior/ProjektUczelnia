package com.example.projektuczelnia.remote

import com.example.projektuczelnia.data.company
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/v3/b/657ecc05dc7465401884536d")
    fun getPost(): Call<company>
}