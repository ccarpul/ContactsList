package com.example.mycontacs.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mycontacs.model.ModelItem
import com.example.mycontacs.utils.ResultWrapper
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ContactsViewModel(private val contactsRespository: ContactsRepository):
    ViewModel(), CoroutineScope {


    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    init { job = SupervisorJob() }

    private val uiModel = MutableLiveData<UiModel>()
    val model: LiveData<UiModel> = uiModel

    private var contacts: ModelItem? = null
    private var contactsError: String? = null

    sealed class UiModel{
        object Loading: UiModel()
        class Content(val contacts: ModelItem?): UiModel()
        object ShowUi: UiModel()
    }

    fun getContacts(){
        launch {
            uiModel.value = UiModel.Loading

            when(val result = contactsRespository.getContacts()){
                is ResultWrapper.Success ->{
                    val responseData = result.value
                    contacts = responseData
                }
                is ResultWrapper.NetworkError -> {
                    contactsError = result.throwable.localizedMessage

                }
                is ResultWrapper.GenericError -> {
                    contactsError = result.error
                }
            }
            uiModel.value = UiModel.Content(contacts)
        }
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}