package com.example.a20220603_ryan_balseiro_nycschools.model
//data class creates a custom variable type that the data coming in from the api call can use
data class School(
    val dbn: String,
    val school_name: String,
    val website: String
)
