package com.example.a20220603_ryan_balseiro_nycschools.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a20220603_ryan_balseiro_nycschools.api.SchoolService
import com.example.a20220603_ryan_balseiro_nycschools.model.School
import com.example.a20220603_ryan_balseiro_nycschools.model.Score
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SchoolViewModel(private val service: SchoolService): ViewModel() {

    /**
     * each live data is used to display the results in the views
     * the MutableLiveData variable is a private variable that the viewModel uses
     * the LiveData variable is the public variable that gets used by the views
     */
    private val _schoolData = MutableLiveData<List<School>>()
    val schoolData: LiveData<List<School>> get() = _schoolData

    private val _scoreData = MutableLiveData<List<Score>>()
    val scoreData: LiveData<List<Score>> get() = _scoreData

    //if the coroutine runs into an error, throws an exception instead of crashing
    val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }

    //coroutine that gets the school list
    fun getAllSchools() {
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            val response = service.getAllSchools()
            if (response.isSuccessful) {
                _schoolData.postValue(response.body()!!)
            } else {
                _schoolData.postValue(emptyList())
            }
        }
    }

    //coroutine that gets the SAT scores
    fun getThisScore(dbn: String) {
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            val response = service.getThisScore(dbn)
            if (response.isSuccessful) {
                _scoreData.postValue(response.body()!!)
            } else {
                _scoreData.postValue(emptyList())
            }
        }
    }
}