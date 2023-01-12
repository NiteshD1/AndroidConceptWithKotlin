package com.android.concept

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.concept.databinding.ActivityDashBoardBinding

class DashBoardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewEmail.text = MainApplication.prefs?.email ?: "Email Not Found"
    }
}