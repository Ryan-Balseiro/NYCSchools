package com.example.a20220603_ryan_balseiro_nycschools.api

import com.example.a20220603_ryan_balseiro_nycschools.model.School
import com.example.a20220603_ryan_balseiro_nycschools.model.Score
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolService {

    //gets the school data as a list of type School
    @GET(SCHOOL_ENDPOINT)
    suspend fun getAllSchools(): Response<List<School>>

    //gets sat data for 1 school (based on the school's dbn) as a list of type Score
    @GET(SCORE_ENDPOINT)
    suspend fun getThisScore(
        @Query("dbn") dbn: String
    ): Response<List<Score>>


    companion object {
        const val BASE_URL = "https://data.cityofnewyork.us/resource/"
        const val SCHOOL_ENDPOINT = "s3k6-pzi2.json"
        const val SCORE_ENDPOINT = "f9bf-2cp4.json"

        var instance: Retrofit? = null
        //creates retrofit instance for api calls
        fun createRetrofitInstance(): Retrofit {
            if (instance == null) {
                instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return instance!!
        }
    }
}