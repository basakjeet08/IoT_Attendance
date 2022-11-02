package iot.lab.qrdetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import iot.lab.qrdetails.repository.Repository


class  AttendanceViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AttendanceViewModel(repository) as T
    }
}

