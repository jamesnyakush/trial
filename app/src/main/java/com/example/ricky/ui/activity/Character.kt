package com.example.ricky.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ricky.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Character : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_activity)
    }
}