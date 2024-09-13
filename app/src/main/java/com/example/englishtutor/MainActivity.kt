package com.example.englishtutor

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.example.englishtutor.views.LevelsActivity

class MainActivity : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val playButton: Button = findViewById(R.id.playButton)
        playButton.setOnClickListener {
            val intent = Intent(this, LevelsActivity::class.java)
            startActivity(intent)
        }

    }
}