package com.example.mycontacs.api

import com.example.mycontacs.utils.Constants
import com.example.mycontacs.model.Model
import com.example.mycontacs.model.ModelContactsItem
import retrofit2.http.GET


interface ApiRest {

    @GET("${Constants.END_POINT}")
    suspend fun getContacts(): ArrayList<ModelContactsItem>
}