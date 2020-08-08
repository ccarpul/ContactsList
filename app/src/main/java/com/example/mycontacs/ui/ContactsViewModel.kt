package com.example.mycontacs.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mycontacs.data.model.ModelContactsItem
import com.example.mycontacs.utils.ResultWrapper
import com.example.mycontacs.utils.customSort
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext

class ContactsViewModel(private val contactsRespository: ContactsRepository) :
    ViewModel(), CoroutineScope {

    var pos = 0

    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    init {
        job = SupervisorJob()
    }

    private val uiModel = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if(uiModel.value == null) refresh()
            return uiModel
        }

    private var contacts: ArrayList<Any> = arrayListOf()
    private var contactsError: String? = null

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val contacts: ArrayList<Any>) : UiModel()
        object ShowUi : UiModel()
    }
     fun getContactAtPosition(): Any{
         return contacts[pos]
     }

    fun getContacts() {
        launch {
            uiModel.value = UiModel.Loading

            when (val result = contactsRespository.getContacts()) {
                is ResultWrapper.Success -> {
                    val responseData = result.value
                    contacts.addAll(customSort(responseData.toList()))
                }
                is ResultWrapper.NetworkError -> {
                    contactsError = result.throwable.localizedMessage
                    Log.d("HandleResponse", "getContacts: $contactsError")

                }
                is ResultWrapper.GenericError -> {
                    contactsError = result.error
                    Log.d("HandleResponse", "getContacts: $contactsError")
                }
            }
            uiModel.value = UiModel.Content(contacts)
        }
    }
    fun refresh(){
        uiModel.value = UiModel.ShowUi
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}