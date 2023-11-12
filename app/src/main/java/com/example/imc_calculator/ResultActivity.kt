package com.example.imc_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.imc_calculator.MainActivity.Companion.BMI_RESULT

class ResultActivity : AppCompatActivity() {
    private lateinit var textViewResult : TextView
    private lateinit var textViewBMI : TextView
    private lateinit var textViewDescription : TextView
    private lateinit var btnRecalculate : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val result: Double = intent.extras?.getDouble(BMI_RESULT) ?: -1.0
        initComponents()
        initUI(result)
        initListeners()
    }

    private fun initListeners() {
        btnRecalculate.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun initUI(result: Double) {
        textViewBMI.text = result.toString()
        when (result) {
            in 0.0..18.5 -> {
                textViewResult.text = getString(R.string.titleUnderweight)
                textViewBMI.setTextColor(getColor(R.color.colorUnderweight))
                textViewDescription.text = getString(R.string.descriptionUnderweight)
            }
            in 18.5..24.9 -> {
                textViewResult.text = getString(R.string.titleNormalWeight)
                textViewBMI.setTextColor(getColor(R.color.colorNormalWeight))
                textViewDescription.text = getString(R.string.descriptionNormalWeight)
            }
            in 25.0..29.9 -> {
                textViewResult.text = getString(R.string.titleOverweight)
                textViewBMI.setTextColor(getColor(R.color.colorOverweight))
                textViewDescription.text = getString(R.string.descriptionOverweight)
            }
            in 30.00..99.9 -> {
                textViewResult.text = getString(R.string.titleObesity)
                textViewBMI.setTextColor(getColor(R.color.colorObesity))
                textViewDescription.text = getString(R.string.descriptionObesity)
            }
            else -> {
                textViewResult.text = getString(R.string.titleError)
                textViewBMI.setTextColor(getColor(R.color.colorObesity))
                textViewDescription.text = getString(R.string.descriptionError)
            }
        }
    }

    private fun initComponents() {
        textViewResult = findViewById(R.id.textViewResult)
        textViewBMI = findViewById(R.id.textViewBMI)
        textViewDescription = findViewById(R.id.textViewDescription)
        btnRecalculate = findViewById(R.id.btnRecalculate)
    }
}