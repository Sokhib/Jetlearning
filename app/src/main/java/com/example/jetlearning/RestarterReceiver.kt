package com.example.jetlearning

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class RestarterReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.startService(Intent(context, SebTrackerService::class.java))
    }
}