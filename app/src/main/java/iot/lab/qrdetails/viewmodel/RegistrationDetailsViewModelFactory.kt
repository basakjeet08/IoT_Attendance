package iot.lab.qrdetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import iot.lab.qrdetails.repository.Repository

class RegistrationDetailsViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegistrationDetailsViewModel(repository) as T
    }
}