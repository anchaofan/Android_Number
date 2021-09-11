package com.example.number

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    var updateText = 1

    fun isPrimeNo(number: Int): Int {
        if(number<2) return 0
        for (i in 2..number/2) {
            if (number % i == 0) {
                return 0
            }
        }
        return 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView2: TextView = findViewById(R.id.textView2)
        val handler = object : Handler() {

            override fun handleMessage(msg: Message) {
                when (msg.arg1) {
                    0 -> textView2.text = "不是素数"
                    1 -> textView2.text = "是素数"
                }
            }
        }

        val btn: Button = findViewById(R.id.btn)
        btn.setOnClickListener{
            val textView: EditText = findViewById(R.id.textView)
            updateText = textView.text.toString().toInt()
            thread {
                val result=isPrimeNo(updateText)
                val msg = Message()
                msg.arg1 = result
                handler.sendMessage(msg)
            }
        }
    }
}
