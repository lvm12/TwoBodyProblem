package co.uk.purpleeagle.betterprojectilemotion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import viewmodel.PmViewModel

class PmViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PmViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return PmViewModel() as T
        }
        throw(IllegalArgumentException("View Model does not exist"))
    }
}