package com.example.jetlearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.jetlearning.ui.theme.JetlearningTheme

class SebTracker : ComponentActivity() {

    private val sebTrackerViewModel: SebTrackerViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetlearningTheme {
                sebTrackerViewModel.log()
            }
        }
    }
}

