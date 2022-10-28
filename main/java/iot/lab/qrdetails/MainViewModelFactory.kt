package iot.lab.qrdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import iot.lab.qrdetails.repository.Repository


class  MainViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}

