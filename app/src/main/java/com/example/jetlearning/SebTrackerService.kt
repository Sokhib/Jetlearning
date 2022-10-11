package com.example.jetlearning

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.gargoylesoftware.htmlunit.BrowserVersion
import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlPage
import kotlinx.coroutines.*


class SebTrackerService : Service() {
    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val url = "https://reserve-prime.apple.com/AE/en_AE/reserve/A/availability?iUP=N"

        scope.launch {
            delay(5000)
            try {
                val webClient = WebClient(BrowserVersion.CHROME)
                webClient.options.apply {
                    isJavaScriptEnabled = true
                    isThrowExceptionOnScriptError = false
                }
                withContext(Dispatchers.IO) {
                    val text = webClient.getPage<HtmlPage>(url).asNormalizedText()
                    val isAvailable =
                        text.contains("We’re not taking reservations to buy iPhone in the store right now.")
                            .not()

                    Log.d("TAG", "isPhoneAvailable: $isAvailable")
                    Log.d("TAG", "Content: $text ")
                }
            } catch (e: Exception) {
                Log.d("TAG", "${e.message}")
            }
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        val broadCastIntent = Intent()
        broadCastIntent.apply {
            action = "restartservice"
            setClass(this@SebTrackerService, RestarterReceiver::class.java)
        }
        sendBroadcast(broadCastIntent)
    }
}
