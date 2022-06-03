package com.example.a20220603_ryan_balseiro_nycschools.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.a20220603_ryan_balseiro_nycschools.api.SchoolService

//this object creates the viewModel for the views to use
object ViewModelHelper {

    fun createService(): SchoolService =
        SchoolService.createRetrofitInstance().create(SchoolService::class.java)

    fun createViewModel(owner: ViewModelStoreOwner) =
        ViewModelProvider(owner, object: ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SchoolViewModel(createService()) as T
            }
        })[SchoolViewModel::class.java]
}