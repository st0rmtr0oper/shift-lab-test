package com.example.shiftlabtest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialog

class SecondActivity : AppCompatActivity() {

    private lateinit var greetingsButton: Button
    private lateinit var greetingsMessage: TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        greetingsButton = findViewById(R.id.greetButton)
        val intent = intent

        greetingsButton.setOnClickListener {
            val dialog = BottomSheetDialog(this)
            //Locating element in another Layout
            val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
            greetingsMessage = view.findViewById(R.id.greetMessage)
            //Getting name form MainActivity
            greetingsMessage.text = intent.getStringExtra("message")
            dialog.setContentView(view)
            dialog.show()
        }
    }
}