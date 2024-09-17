package com.example.englishtutor.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.englishtutor.R
import com.example.englishtutor.chooseOption.ChooseOptionLogic
import com.example.englishtutor.chooseOption.OptionToSelect

class StartGame : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_start_game)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val levelString = intent.getStringExtra("level").orEmpty();
        val messageString = intent.getStringExtra("message").orEmpty();
        val textLevel = findViewById<TextView>(R.id.levelText)
        textLevel.text =levelString

        val motivatioText = findViewById<TextView>(R.id.motivationText)
        motivatioText.text = messageString

        val image1: ImageView = findViewById(R.id.completeWords)
        val image2: ImageView = findViewById(R.id.bottomIcon)
        val image3: ImageView = findViewById(R.id.traductorIcon)

        val numImages = getNumImages(levelString)
        when (numImages) {
            1 -> {
                image1.visibility = View.GONE
                image2.visibility = View.VISIBLE
                image3.visibility = View.GONE
            }
            2 -> {
                image1.visibility = View.VISIBLE
                image2.visibility = View.VISIBLE
                image3.visibility = View.GONE
            }
            3 -> {
                image1.visibility = View.VISIBLE
                image2.visibility = View.VISIBLE
                image3.visibility = View.VISIBLE
            }
        }

        val startBnt: Button = findViewById(R.id.startButton)
        startBnt.setOnClickListener {
            val intent = Intent(this, ChooseOption::class.java)
            intent.putExtra("title", "Escoja la opcion correcta")
            intent.putExtra("level", levelString)

            intent.putExtra("levelProgress", 10)  // to change if add more exercises
            val chooseOptionLogic = ChooseOptionLogic(this, levelString)
            val optionsGame: List<OptionToSelect>  = chooseOptionLogic.generateOptions(10);  // chage to add mor exerc.
            intent.putExtra("optionsGame", ArrayList(optionsGame))

            val arrayPoints: ArrayList<Int> = ArrayList()
            intent.putIntegerArrayListExtra("arrayPoints", arrayPoints)
            startActivity(intent)
        }
    }

    fun getNumImages(level: String) : Int {
        val levelLower: String  = level.lowercase()
        if (levelLower.contains("inicial"))
            return 1
        if (levelLower.contains("intermedio"))
            return 2
        if (levelLower.contains("avanzado"))
            return 3
        return 3
    }
}