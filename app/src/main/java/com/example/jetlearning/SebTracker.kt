package com.example.jetlearning

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.jetlearning.ui.theme.JetlearningTheme

class SebTracker : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetlearningTheme {
                val serviceIntent = Intent(this, SebTrackerService::class.java)
                startService(serviceIntent)
            }
        }
    }
//
//    override fun onDestroy() {
//        val broadCastIntent = Intent()
//        broadCastIntent.apply {
//            action = "restartservice"
//            setClass(this@SebTracker, RestarterReceiver::class.java)
//        }
//        sendBroadcast(broadCastIntent)
//        super.onDestroy()
//    }
}

