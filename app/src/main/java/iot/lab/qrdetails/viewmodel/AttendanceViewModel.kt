package iot.lab.qrdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iot.lab.qrdetails.model.Data
import iot.lab.qrdetails.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class AttendanceViewModel(private val repository: Repository): ViewModel() {

    private val _myResponse : MutableLiveData<Response<Data>> = MutableLiveData()
    val myResponse : LiveData<Response<Data>>
        get() = _myResponse

    //This calls the repository and ask it to fetch data of roll without filter
    fun getPost(number : String) {
        viewModelScope.launch {
            val response: Response<Data> = repository.getPost(number)
            _myResponse.value = response
        }
    }

    //This calls the repository and ask it to fetch data of roll of a particular day
    fun getPostOfFixedDay(rollNumber : String , inTimeDay : String , inTimeMonth : String, inTimeYear : String){
        viewModelScope.launch {
            val response : Response<Data> = repository.getPostOfFixedDay(rollNumber , inTimeDay , inTimeMonth , inTimeYear)
            _myResponse.value = response
        }
    }

    //This calls the repository and ask it to fetch data of roll within a range
    fun getPostBetweenDays(rollNumber : String , inTimeBetween : String){
        viewModelScope.launch {
            val response : Response<Data> = repository.getPostBetweenDays(rollNumber , inTimeBetween)
            _myResponse.value = response
        }
    }

}