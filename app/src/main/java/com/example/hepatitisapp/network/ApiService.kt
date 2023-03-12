package com.example.hepatitisapp.network

import com.example.hepatitisapp.response.PredictMortalityResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("predict")
    fun postPredictMortality(
        @Field("age") age:String,
        @Field("sex") sex:String,
        @Field("steroid") steroid:String,
        @Field("antivirals") antivirals:String,
        @Field("fatigue") fatigue:String,
        @Field("spiders") spiders:String,
        @Field("varices") varices:String,
        @Field("ascites") ascites:String,
        @Field("bilirubin") bilirubin:String,
        @Field("alk_phosphate") alk_phosphate:String,
        @Field("sgot") sgot:String,
        @Field("albumin") albumin:String,
        @Field("protime") protime:String,
        @Field("histology") histology:String
    ): Call<PredictMortalityResponse>
}