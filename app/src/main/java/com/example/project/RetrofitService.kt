package com.example.project

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {

    @FormUrlEncoded
    @POST("/save")
    fun save(@Field("name") name:String, @Field("serial") serial:String, @Field("state") state:String,
             @Field("type") type:String, @Field("user") user:String): Call<ResultItem>

    @GET("/get")
    fun get():Call<ArrayList<Item>>

    @FormUrlEncoded
    @POST("/delete")
    fun delete(@Field("idNm") idNm:String): Call<ResultItem>
}