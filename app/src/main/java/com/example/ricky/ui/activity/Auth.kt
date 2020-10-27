package com.example.ricky.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ricky.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Auth : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.auth_activity)
    }
}