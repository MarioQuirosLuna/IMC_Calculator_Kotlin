package com.example.imc_calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var currentHeight: Int = 120
    private var currentWeight: Int = 80

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var textViewHeight: TextView
    private lateinit var textViewWeight: TextView
    private lateinit var rangeSliderHeight: RangeSlider
    private lateinit var btnRemoveWeight: FloatingActionButton
    private lateinit var btnAddWeight: FloatingActionButton
    private lateinit var btnCalculate: Button

    companion object {
        const val BMI_RESULT = "IMC_RESULT" //This is a constant that will be used to pass the result to the ResultActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponent()
        initListeners()
        initUI()
    }

    private fun initComponent() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        textViewHeight = findViewById(R.id.textViewHeight)
        textViewHeight.text = currentHeight.toString()
        rangeSliderHeight = findViewById(R.id.rangeSliderHeight)
        textViewWeight = findViewById(R.id.textViewWeight)
        textViewWeight.text = currentWeight.toString()
        btnRemoveWeight = findViewById(R.id.btnRemoveWeight)
        btnAddWeight = findViewById(R.id.btnAddWeight)
        btnCalculate = findViewById(R.id.btnCalculate)
    }

    private fun initListeners() {
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        rangeSliderHeight.addOnChangeListener { _, value, _ ->
            currentHeight = value.toInt()
            setHeight()
        }
        btnRemoveWeight.setOnClickListener {
            currentWeight--
            setWeight()
        }
        btnAddWeight.setOnClickListener {
            currentWeight++
            setWeight()
        }
        btnCalculate.setOnClickListener{
            val result = calculateIMC()
            navigateToResult(result)
        }
    }

    private fun navigateToResult(result: Double) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(BMI_RESULT, result)
        startActivity(intent)
    }

    private fun calculateIMC():Double {
        val df = DecimalFormat("#.##")
        val imc = currentWeight / (currentHeight.toDouble()/100 * currentHeight.toDouble()/100)
        return df.format(imc).toDouble()
    }

    private fun setHeight(){
        var result = currentHeight.toString()
        result += " cm"
        textViewHeight.text = result
    }
    private fun setWeight(){
        textViewWeight.text = currentWeight.toString()
    }

    private fun changeGender(){
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun setGenderColor(){
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponent: Boolean) : Int{
        val colorReference = if(isSelectedComponent){
            R.color.background_component_selected
        }else{
            R.color.background_component
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI() {
        setGenderColor()
        setWeight()
        setHeight()
    }
}