package iot.lab.qrdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iot.lab.qrdetails.model.Data
import iot.lab.qrdetails.model.Post
import iot.lab.qrdetails.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    private val _myResponse : MutableLiveData<Response<Data>> = MutableLiveData()

    val myResponse : LiveData<Response<Data>>
        get() = _myResponse


    fun getPost(number : String) {
        viewModelScope.launch {
            val response: Response<Data> = repository.getPost(number)
            _myResponse.value = response
        }
    }

    //Function added by Anirban Basak
    fun getPostOfFixedDay(rollNumber : String , inTimeDay : String , inTimeMonth : String, inTimeYear : String){
        viewModelScope.launch {
            val response : Response<Data> = repository.getPostOfFixedDay(rollNumber , inTimeDay , inTimeMonth , inTimeYear)
            _myResponse.value = response
        }
    }

    //Function added by Anirban Basak
    fun getPostBetweenDays(rollNumber : String , inTimeBetween : String){
        viewModelScope.launch {
            val response : Response<Data> = repository.getPostBetweenDays(rollNumber , inTimeBetween)
            _myResponse.value = response
        }
    }

}