package com.example.englishtutor.views

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.englishtutor.R
import com.example.englishtutor.chooseOption.ChooseOptionLogic
import com.example.englishtutor.chooseOption.OptionToSelect

class ChooseOption : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_choose_option)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val levelString = intent.getStringExtra("level").orEmpty()

        val arrayPoints: ArrayList<Int>? = intent.getIntegerArrayListExtra("arrayPoints")

        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val maxProgress: Int = 100

        val levelProgres = intent.getIntExtra("levelProgress",0)

        progressBar.progress = levelProgres
        progressBar.max = maxProgress

        val title = intent.getStringExtra("title").orEmpty();
        val titleView: TextView = findViewById<TextView>(R.id.textViewTitle)
        titleView.text = title
        // over there
       // val chooseOptionLogic = ChooseOptionLogic(this, levelString)
        val optionsGame = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("optionsGame", ArrayList::class.java) as? ArrayList<OptionToSelect>
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("optionsGame") as? ArrayList<OptionToSelect>
        }
        val indexScreen = intent.getIntExtra("indexScreen", 0)
        val btn2: Button = findViewById(R.id.button2)
        val btn1: Button = findViewById(R.id.button1)
        val btn3: Button = findViewById(R.id.button3)
        val btn4: Button = findViewById(R.id.button4)
        val imageChoose: ImageView = findViewById(R.id.imageView)

        btn2.text = optionsGame?.get(indexScreen)!!.options[0]
        btn3.text = optionsGame?.get(indexScreen)!!.options[2]
        btn1.text = optionsGame?.get(indexScreen)!!.options[1]
        btn4.text = optionsGame?.get(indexScreen)!!.options[3]
        imageChoose.setImageResource(optionsGame.get(indexScreen).pathImage)

        val textHelp: TextView = findViewById(R.id.textAns)

        verifyBtn(btn1, optionsGame?.get(indexScreen)!!.correctAns, arrayPoints, textHelp, btn2, btn3, btn4)
        verifyBtn(btn2, optionsGame?.get(indexScreen)!!.correctAns, arrayPoints, textHelp, btn1, btn3, btn4)
        verifyBtn(btn3, optionsGame?.get(indexScreen)!!.correctAns, arrayPoints, textHelp, btn1, btn2, btn4)
        verifyBtn(btn4, optionsGame?.get(indexScreen)!!.correctAns, arrayPoints, textHelp, btn1, btn2, btn3)

        val btnSig: Button = findViewById(R.id.buttonSig)
        val btnAnt: Button = findViewById(R.id.buttonAnt)

        goNextScreen(btnSig, levelString, levelProgres, optionsGame, indexScreen, arrayPoints, levelString)
    }

    fun goNextScreen(btnSig:Button, level:String, progress:Int, optionsGame: List<OptionToSelect>, indexScreen: Int, arrayPoints:ArrayList<Int>?, levelString: String) {
        btnSig.setOnClickListener {

            if (progress == 100) {
                val endIntentSc = Intent(this, EndLevel::class.java)
                val percetPoints:Int = getPercentPoints(arrayPoints, 10)
                endIntentSc.putExtra("percetPoints", percetPoints)
                if (percetPoints == 100)
                    endIntentSc.putExtra("showConfetti", true)
                endIntentSc.putExtra("level", levelString)
                startActivity(endIntentSc)
                finish()
            } else {
                val nextIntent = Intent(this, ChooseOption::class.java)
                nextIntent.putExtra("indexScreen", indexScreen + 1)

                nextIntent.putExtra("title", "Escoja la opcion correcta")
                nextIntent.putExtra("level", level)

                nextIntent.putExtra(
                    "levelProgress",
                    progress + 10
                )  // to change if add more exercises
                val chooseOptionLogic = ChooseOptionLogic(this, level)
                nextIntent.putExtra("optionsGame", ArrayList(optionsGame))

                nextIntent.putIntegerArrayListExtra("arrayPoints", arrayPoints)
                startActivity(nextIntent)
                finish()
            }
        }
    }

    fun getPercentPoints(arrayPoints: ArrayList<Int>?, maxValue: Int): Int {
        if (arrayPoints.isNullOrEmpty()) {
            return 0
        }
        val totalPoints = arrayPoints.sum()
        Log.d("SUM",totalPoints.toString())
        val averagePoints = totalPoints.toDouble() / arrayPoints.size
        val percentage = (averagePoints / maxValue) * 100
        return percentage.toInt()
    }

    fun verifyBtn(btn:Button, correctAns:String, arrayPoints:ArrayList<Int>?, textHelp:TextView, vararg otherButtons: Button) {
        btn.setOnClickListener {
            if (btn.text.equals(correctAns)) {
                btn.setBackgroundResource(R.drawable.correct_ans_background)
                textHelp.setTextColor(ContextCompat.getColor(this, R.color.correct_ans))
                textHelp.text = "Correcto !"
                arrayPoints?.add(10)
            } else {
                btn.setBackgroundResource(R.drawable.incorrect_ans_background)
                textHelp.setTextColor(ContextCompat.getColor(this, R.color.incorrect_ans))
                textHelp.text = "Incorrecto !"
                arrayPoints?.add(0)
            }
            btn.isEnabled = false
            otherButtons.forEach { it.isEnabled = false }

        }
    }
}