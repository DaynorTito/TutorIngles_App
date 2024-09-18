package com.example.englishtutor.views

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.englishtutor.R
import com.example.englishtutor.chooseOption.ChooseOptionLogic
import com.example.englishtutor.chooseOption.OptionToSelect
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.core.models.Shape
import nl.dionsegijn.konfetti.core.models.Size
import nl.dionsegijn.konfetti.xml.KonfettiView
import java.util.concurrent.TimeUnit

class EndLevel : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_end_level)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val showConfetti = intent.getBooleanExtra("showConfetti", false)
        val mainTitle: TextView = findViewById(R.id.textView)
        val messageRec: TextView = findViewById(R.id.textView2)
        val imageShow: ImageView = findViewById(R.id.imageView2)
        val levelStr: String = intent.getStringExtra("level").orEmpty()
        if (showConfetti) {
            showConfettiEffect()
            mainTitle.text = "Felicidades completaste el nivel con exito!"
            messageRec.text = "Obtuviste 5 estrellas en este nivel, desbloqueaste un nuevo nivel, ve a pasarlo tambien en la lista de niveles"
            imageShow.setImageResource(R.drawable.medal)
        } else {
            mainTitle.text = "Lo siento no pudiste completar el nivel"
            messageRec.text = "Tu calificacion fue de menos de 5 estrellas, necesitas mejorar, intentalo de nuevo!"
            imageShow.setImageResource(R.drawable.sad_face)
        }


        val percetageUser: Int = intent.getIntExtra("percetPoints", 0)
        val ratingBar: RatingBar = findViewById(R.id.ratingBar)
        ratingBar.progressTintList = ColorStateList.valueOf(Color.YELLOW)
        val porcentaje = percetageUser
        val maxStars = ratingBar.numStars
        val rating = (porcentaje / 100.0) * maxStars
        ratingBar.rating = rating.toFloat()

        val btnClose: Button = findViewById(R.id.closeBtn)
        btnClose.setOnClickListener {
            val intent = Intent(this, LevelsActivity::class.java)
            intent.putExtra("level", levelStr)
            intent.putExtra("isPass", porcentaje)
            startActivity(intent)
            finish()
        }
    }


    private fun showConfettiEffect() {
        val konfettiView: KonfettiView = findViewById(R.id.konfettiView)
        val party = Party(
            speed = 30f,
            maxSpeed = 50f,
            damping = 0.9f,
            spread = 360,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
            position = Position.Relative(0.5, 0.3),
            emitter = Emitter(duration = 1000, TimeUnit.MILLISECONDS).max(200),
            timeToLive = 2000L,
            shapes = listOf(Shape.Rectangle(0.2f), Shape.Rectangle(0.1f), Shape.Circle),
            size = listOf(Size(8), Size(12), Size(16))
        )
        konfettiView.start(party)
    }

}