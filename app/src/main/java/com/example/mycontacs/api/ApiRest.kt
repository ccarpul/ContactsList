package com.example.mycontacs.api

import com.example.mycontacs.utils.Constants
import com.example.mycontacs.model.ModelItem
import retrofit2.http.GET


interface ApiRest {

    @GET("${Constants.END_POINT}")
    suspend fun getContacts(): ModelItem
}