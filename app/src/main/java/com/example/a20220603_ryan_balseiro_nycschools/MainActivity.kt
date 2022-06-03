package com.example.a20220603_ryan_balseiro_nycschools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a20220603_ryan_balseiro_nycschools.databinding.ActivityMainBinding
import com.example.a20220603_ryan_balseiro_nycschools.viewmodel.ViewModelHelper

class MainActivity : AppCompatActivity() {
    //declaration of the binding for the views in the ui
    lateinit var binding: ActivityMainBinding
    //creates the viewModel by calling the ViewModelHelper object
    private val viewModel by lazy {
        ViewModelHelper.createViewModel(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getAllSchools()
    }
}