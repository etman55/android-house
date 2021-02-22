package com.atef.clubhouse.cloudmessaging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.atef.clubhouse.base.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StatusManager @Inject constructor() {

    private val _status = MutableLiveData<Resource<CaseStatus?>>()
    val status: LiveData<Resource<CaseStatus?>>
        get() = _status

    fun updateStatus(resource: Resource<CaseStatus?>) {
        _status.postValue(resource)
    }
}
