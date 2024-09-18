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
        val secondLevelButton: Button = findViewById(R.id.botonPreIntermedio)
        val thirdLevelButton: Button = findViewById(R.id.botonIntermedio)
        val fourthLevelButton: Button = findViewById(R.id.botonPreAvanzado)
        val fivethLevelButton: Button = findViewById(R.id.botonAvanzado)
        val sixthLevelButton: Button = findViewById(R.id.botonExperto)
        val levelStr: String = intent.getStringExtra("level").orEmpty()
        val percent: Int = intent.getIntExtra("isPass", 0)
        availableButton(secondLevelButton, thirdLevelButton, fourthLevelButton, fivethLevelButton, sixthLevelButton, levelStr, percent)

        firstLevelButton.setOnClickListener {
            val intent = Intent(this, StartGame::class.java)
            intent.putExtra("level", "Nivel: Inicial")
            intent.putExtra("message", "Estas empezando con el nivel inicial, en los ejercicios deberas de escoger la opcion correcta segun corresponda")
            startActivity(intent)
            finish()
        }

        secondLevelButton.setOnClickListener {
            val intent = Intent(this, StartGame::class.java)
            intent.putExtra("level", "Nivel: Pre Intermedio")
            intent.putExtra("message", "Ya no eres un noob pasaste el inicial, ahora ademas se agregaron ejercicios de traduccion")
            startActivity(intent)
            finish()
        }

        thirdLevelButton.setOnClickListener {
            val intent = Intent(this, StartGame::class.java)
            intent.putExtra("level", "Nivel: Intermedio")
            intent.putExtra("message", "Tienes un nivel intermedio, continua con los mismos ejercicios con algo mas de nivel pero con mas dificultad")
            startActivity(intent)
            finish()
        }

        fourthLevelButton.setOnClickListener {
            val intent = Intent(this, StartGame::class.java)
            intent.putExtra("level", "Nivel: Pre Avanzado")
            intent.putExtra("message", "Pasaste los 3 primeros niveles, ahora se agregaran ejercicios para completar oraciones con un poco de dificultad")
            startActivity(intent)
            finish()
        }

        fivethLevelButton.setOnClickListener {
            val intent = Intent(this, StartGame::class.java)
            intent.putExtra("level", "Nivel: Avanzado")
            intent.putExtra("message", "Tienes un buen nivel de ingles, ahora debes resolver ejercicios del mismo tipo con mas dificultad")
            startActivity(intent)
            finish()
        }

        sixthLevelButton.setOnClickListener {
            val intent = Intent(this, StartGame::class.java)
            intent.putExtra("level", "Nivel: Experto")
            intent.putExtra("message", "Wow, eres verdaderamente bueno, solo un poco mas para ser un verdadero experto en Ingles")
            startActivity(intent)
            finish()
        }

    }

    private fun availableButton(
        secondLevelButton: Button,
        thirdLevelButton: Button,
        fourthLevelButton: Button,
        fivethLevelButton: Button,
        sixthLevelButton: Button,
        levelStr: String,
        percent: Int
    ) {
        if (percent == 100) {
            val newLevel = getNextLevel(levelStr)
            allowBotons(newLevel, secondLevelButton, thirdLevelButton,  fourthLevelButton, fivethLevelButton, sixthLevelButton)
        } else {
            allowBotons(levelStr, secondLevelButton, thirdLevelButton,  fourthLevelButton, fivethLevelButton, sixthLevelButton)
        }
    }

    fun allowBotons(level:String, secondLevelButton: Button,
                    thirdLevelButton: Button,
                    fourthLevelButton: Button,
                    fivethLevelButton: Button,
                    sixthLevelButton: Button,) {
        if (level.lowercase().contains("pre intermedio")) {
            habilitarBotonYQuitarCandado(secondLevelButton)
        } else if (level.lowercase().contains("intermedio")) {
            habilitarBotonYQuitarCandado(secondLevelButton)
            habilitarBotonYQuitarCandado(thirdLevelButton)
        } else if (level.lowercase().contains("pre avanzado")) {
            habilitarBotonYQuitarCandado(secondLevelButton)
            habilitarBotonYQuitarCandado(thirdLevelButton)
            habilitarBotonYQuitarCandado(fourthLevelButton)
        } else if (level.lowercase().contains("avanzado")) {
            habilitarBotonYQuitarCandado(secondLevelButton)
            habilitarBotonYQuitarCandado(thirdLevelButton)
            habilitarBotonYQuitarCandado(fourthLevelButton)
            habilitarBotonYQuitarCandado(fivethLevelButton)
        } else if (level.lowercase().contains("experto")) {
            habilitarBotonYQuitarCandado(secondLevelButton)
            habilitarBotonYQuitarCandado(thirdLevelButton)
            habilitarBotonYQuitarCandado(fourthLevelButton)
            habilitarBotonYQuitarCandado(fivethLevelButton)
            habilitarBotonYQuitarCandado(sixthLevelButton)
        }

    }

    fun habilitarBotonYQuitarCandado(boton: Button) {
        if (!boton.isEnabled) {
            boton.isEnabled = true
            boton.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
        }
    }

    private fun getNextLevel(levelStr: String): String{
        if (levelStr.lowercase().contains("inicial"))
            return "pre intermedio"
        else if (levelStr.lowercase().contains("pre intermedio"))
            return "intermedio"
        else if (levelStr.lowercase().contains("intermedio"))
            return "pre avanzado"
        else if (levelStr.lowercase().contains("pre avanzado"))
            return "avanzado"
        else if (levelStr.lowercase().contains("avanzado"))
            return "experto"
        return "experto"
    }
}