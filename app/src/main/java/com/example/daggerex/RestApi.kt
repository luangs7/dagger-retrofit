package com.example.daggerex

import io.reactivex.Observable
import retrofit2.http.GET


interface RestApi {
    @GET("/posts")
    fun getPost() : Observable<List<Post>>
}