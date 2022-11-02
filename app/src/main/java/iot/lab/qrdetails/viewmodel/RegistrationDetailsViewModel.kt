package iot.lab.qrdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iot.lab.qrdetails.model.EventData
import iot.lab.qrdetails.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class RegistrationDetailsViewModel(private val repository: Repository) : ViewModel() {

    private val _myResponse : MutableLiveData<Response<EventData>> = MutableLiveData()
    val myResponse : LiveData<Response<EventData>>
        get() = _myResponse

    //This calls the repository and ask it to fetch Registration Details of roll
    fun getRegistrationDetails(rollNumber : String) {
        viewModelScope.launch {
            val response = repository.getRegistrationDetails(rollNumber)
            _myResponse.value = response
        }
    }
}