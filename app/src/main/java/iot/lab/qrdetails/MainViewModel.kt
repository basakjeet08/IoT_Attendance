package iot.lab.qrdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iot.lab.qrdetails.model.Data
import iot.lab.qrdetails.model.Post
import iot.lab.qrdetails.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Response<Data>> = MutableLiveData()
    fun getPost(number : String) {
        viewModelScope.launch {
            val response: Response<Data> = repository.getPost(number)
            myResponse.value = response
        }
    }
}