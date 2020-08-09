package com.example.mycontacs.data.api

import com.example.mycontacs.utils.Constants
import com.example.mycontacs.data.model.ModelContactsItem
import retrofit2.http.GET


interface ApiRest {

    @GET(Constants.END_POINT)
    suspend fun getContacts(): ArrayList<ModelContactsItem>
}