package com.example.travelcompanionapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // UI reference variables
    private lateinit var spinnerCategory: Spinner
    private lateinit var spinnerFrom: Spinner
    private lateinit var spinnerTo: Spinner
    private lateinit var editValue: EditText
    private lateinit var btnConvert: Button
    private lateinit var textResult: TextView

    // Categories and Units as per Subtask 1 & 2
    private val categories = listOf("Currency", "Fuel & Distance", "Temperature")
    private val currencyUnits = listOf("USD", "AUD", "EUR", "JPY", "GBP")
    private val fuelUnits = listOf("mpg", "km/L", "Gallon (US)", "Liter", "Nautical Mile", "Kilometer")
    private val tempUnits = listOf("Celsius", "Fahrenheit", "Kelvin")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupSpinners()

        // Handle the conversion when button is clicked
        btnConvert.setOnClickListener { handleConvert() }
    }

    private fun initViews() {
        spinnerCategory = findViewById(R.id.spinnerCategory)
        spinnerFrom = findViewById(R.id.spinnerFrom)
        spinnerTo = findViewById(R.id.spinnerTo)
        editValue = findViewById(R.id.editValue)
        btnConvert = findViewById(R.id.btnConvert)
        textResult = findViewById(R.id.textResult)
    }

    private fun setupSpinners() {
        // Set main category adapter
        spinnerCategory.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories)

        // Load initial currency units
        updateUnitSpinners(currencyUnits)

        // Dynamic unit update based on category selection
        spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, pos: Int, id: Long) {
                when (categories[pos]) {
                    "Currency" -> updateUnitSpinners(currencyUnits)
                    "Fuel & Distance" -> updateUnitSpinners(fuelUnits)
                    "Temperature" -> updateUnitSpinners(tempUnits)
                }
                textResult.text = "Result: ---"
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun updateUnitSpinners(units: List<String>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, units)
        spinnerFrom.adapter = adapter
        spinnerTo.adapter = adapter
    }

    private fun handleConvert() {
        val category = spinnerCategory.selectedItem.toString()
        val fromUnit = spinnerFrom.selectedItem.toString()
        val toUnit = spinnerTo.selectedItem.toString()
        val input = editValue.text.toString().trim()

        // Subtask 4: Validation for empty or non-numeric input
        if (input.isEmpty()) {
            editValue.error = "Please enter a value"
            Toast.makeText(this, "Input cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        val value = input.toDoubleOrNull()
        if (value == null) {
            editValue.error = "Numbers only"
            Toast.makeText(this, "Please enter a numeric value", Toast.LENGTH_SHORT).show()
            return
        }

        // Subtask 4: Handle Identity Conversions (same unit selected)
        if (fromUnit == toUnit) {
            Toast.makeText(this, "Identity Conversion: Same units selected", Toast.LENGTH_SHORT).show()
            textResult.text = String.format("Result: %.2f (%s → %s)", value, fromUnit, toUnit)
            return
        }

        // Subtask 4: Prevent negative values for Fuel & Distance
        if (category == "Fuel & Distance" && value < 0) {
            Toast.makeText(this, "Fuel/Distance values cannot be negative", Toast.LENGTH_SHORT).show()
            return
        }

        // Route to specific conversion logic
        val result = when (category) {
            "Currency" -> convertCurrency(fromUnit, toUnit, value)
            "Fuel & Distance" -> convertFuelAndDistance(fromUnit, toUnit, value)
            "Temperature" -> convertTemperature(fromUnit, toUnit, value)
            else -> 0.0
        }

        // Final result formatting for display
        textResult.text = String.format("Result: %.4f (%s → %s)", result, fromUnit, toUnit)
    }

    // --- Subtask 2: Conversion Mathematics ---

    // Currency logic using USD as base with fixed 2026 rates
    private fun convertCurrency(from: String, to: String, value: Double): Double {
        val usdBase = when (from) {
            "AUD" -> value / 1.55
            "EUR" -> value / 0.92
            "JPY" -> value / 148.50
            "GBP" -> value / 0.78
            else -> value // is USD
        }
        return when (to) {
            "AUD" -> usdBase * 1.55
            "EUR" -> usdBase * 0.92
            "JPY" -> usdBase * 148.50
            "GBP" -> usdBase * 0.78
            else -> usdBase
        }
    }

    // Combined Fuel, Volume, and Distance logic using specified factors
    private fun convertFuelAndDistance(from: String, to: String, value: Double): Double {
        return when {
            // mpg to km/L (Factor: 0.425)
            from == "mpg" && to == "km/L" -> value * 0.425
            from == "km/L" && to == "mpg" -> value / 0.425
            // Volume: Gallon to Liter (Factor: 3.785)
            from == "Gallon (US)" && to == "Liter" -> value * 3.785
            from == "Liter" && to == "Gallon (US)" -> value / 3.785
            // Distance: Nautical Mile to Kilometer (Factor: 1.852)
            from == "Nautical Mile" && to == "Kilometer" -> value * 1.852
            from == "Kilometer" && to == "Nautical Mile" -> value / 1.852
            else -> value
        }
    }

    // Temperature logic using Celsius as base
    private fun convertTemperature(from: String, to: String, value: Double): Double {
        val celsiusBase = when (from) {
            "Fahrenheit" -> (value - 32.0) / 1.8
            "Kelvin" -> value - 273.15
            else -> value // is Celsius
        }
        return when (to) {
            "Fahrenheit" -> (celsiusBase * 1.8) + 32.0
            "Kelvin" -> celsiusBase + 273.15
            else -> celsiusBase
        }
    }
}