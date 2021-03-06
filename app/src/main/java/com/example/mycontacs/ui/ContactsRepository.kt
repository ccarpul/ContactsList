package com.example.mycontacs.ui

import com.example.mycontacs.data.api.ApiRest
import com.example.mycontacs.data.model.ModelContactsItem
import com.example.mycontacs.utils.ResultWrapper
import com.example.mycontacs.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactsRepository(private val apiRest: ApiRest) {

    suspend fun getContacts(): ResultWrapper<ArrayList<ModelContactsItem>> =
        safeApiCall(Dispatchers.IO) { apiRest.getContacts() }

}