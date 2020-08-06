package com.example.mycontacs.ui

import com.example.mycontacs.api.ApiRest
import com.example.mycontacs.model.ModelItem
import com.example.mycontacs.utils.ResultWrapper
import com.example.mycontacs.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactsRepository(private val apiRest: ApiRest) {

    val dispatcher = Dispatchers.IO

    suspend fun getContacts(): ResultWrapper<ModelItem> =
        withContext(Dispatchers.IO) {
            safeApiCall(dispatcher) { apiRest.getContacts() }
        }
}