package com.example.jetlearning

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class SebTrackerViewModel : ViewModel() {
    init {
        viewModelScope.launch {
            sebScraper()
        }
    }


    private suspend fun sebScraper() {
        val url = "https://reserve-prime.apple.com/AE/en_AE/reserve/A/availability?iUP=N"
        try {
            withContext(Dispatchers.IO) {
                val doc = Jsoup.connect(url).get()
                val text = doc.select("div.typography-headline-reduced").text()
                val isAvailable =
                    text.contains("We’re not taking reservations to buy iPhone in the store right now.")
                        .not()
                Log.d(
                    "TAG", "Doc is: ${doc.body()} " +
                            "" +
                            "Text is: $text\nIphone is available: $isAvailable"
                )
            }
        } catch (e: Exception) {
            Log.d("TAG", "${e.message}")
        }
    }

    fun log() {
        Log.d("TAG", "ViewModel is initialized")
    }
}