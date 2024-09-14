package com.example.englishtutor.views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.example.englishtutor.R

class LevelsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_levels)

        val firstLevelButton: Button = findViewById(R.id.botonInicial)
        firstLevelButton.setOnClickListener {
            val intent = Intent(this, StartGame::class.java)
            intent.putExtra("level", "Nivel: Inicial")
            intent.putExtra("message", "Estas empezando con el nivel inicial, en los ejercicios deberas de escoger la opcion correcta segun corresponda")
            startActivity(intent)
        }

        val secondLevelButton: Button = findViewById(R.id.botonPreIntermedio)

        secondLevelButton.setOnClickListener {
            val intent = Intent(this, StartGame::class.java)
            intent.putExtra("level", "Nivel: Pre Intermedio")
            intent.putExtra("message", "Ya no eres un noob pasaste el inicial, ahora ademas se agregaron ejercicios de traduccion")
            startActivity(intent)
        }
        val thirdLevelButton: Button = findViewById(R.id.botonIntermedio)

        thirdLevelButton.setOnClickListener {
            val intent = Intent(this, StartGame::class.java)
            intent.putExtra("level", "Nivel: Intermedio")
            intent.putExtra("message", "Tienes un nivel intermedio, continua con los mismos ejercicios con algo mas de nivel pero con mas dificultad")
            startActivity(intent)
        }
        val fourthLevelButton: Button = findViewById(R.id.botonPreAvanzado)
        fourthLevelButton.setOnClickListener {
            val intent = Intent(this, StartGame::class.java)
            intent.putExtra("level", "Nivel: Pre Avanzado")
            intent.putExtra("message", "Pasaste los 3 primeros niveles, ahora se agregaran ejercicios para completar oraciones con un poco de dificultad")
            startActivity(intent)
        }
        val fivethLevelButton: Button = findViewById(R.id.botonAvanzado)
        fivethLevelButton.setOnClickListener {
            val intent = Intent(this, StartGame::class.java)
            intent.putExtra("level", "Nivel: Avanzado")
            intent.putExtra("message", "Tienes un buen nivel de ingles, ahora debes resolver ejercicios del mismo tipo con mas dificultad")
            startActivity(intent)
        }

        val sixthLevelButton: Button = findViewById(R.id.botonExperto)
        sixthLevelButton.setOnClickListener {
            val intent = Intent(this, StartGame::class.java)
            intent.putExtra("level", "Nivel: Experto")
            intent.putExtra("message", "Wow, eres verdaderamente bueno, solo un poco mas para ser un verdadero experto en Ingles")
            startActivity(intent)
        }

    }
}