package com.ozdmromer.cryptocurrencyapp.presentation.coin_price_web_socket

import android.util.Log
import com.google.gson.Gson
import com.ozdmromer.cryptocurrencyapp.data.remote.dto.BinanceTicker
import okhttp3.*

class BinanceWebSocketClient {

    private var webSocket: WebSocket? = null

    fun connect(coinParameter: String, onPriceUpdate: (BinanceTicker) -> Unit) {
        val request = Request.Builder()
            .url("wss://stream.binance.com:9443/ws/$coinParameter@ticker")
            .build()

        val client = OkHttpClient()

        webSocket = client.newWebSocket(request, object : WebSocketListener() {

            override fun onOpen(webSocket: WebSocket, response: Response) {
                Log.d("WebSocket", "Bağlantı açıldı")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                val ticker = try {
                    val gson = Gson()
                    gson.fromJson(text, BinanceTicker::class.java)
                } catch (e: Exception) {
                    null
                }
                ticker?.let { onPriceUpdate(it) }
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                Log.d("WebSocket", "Kapanıyor: $reason")
                webSocket.close(1000, null)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                Log.e("WebSocket", "Hata: ${t.message}")
            }
        })
    }

    fun disconnect() {
        webSocket?.close(1000, "Kapatılıyor")
    }
}
