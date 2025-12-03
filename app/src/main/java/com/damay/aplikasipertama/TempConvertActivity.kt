package com.damay.aplikasipertama

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TempConvertActivity : AppCompatActivity() {

    // Declare UI components
    private lateinit var etInput: EditText
    private lateinit var etOutput: EditText
    private lateinit var spinnerSuhuInput: Spinner
    private lateinit var spinnerSuhuOutput: Spinner
    private lateinit var btnConvert: Button
    private lateinit var tvSaDeng: TextView

    // Variables to store selected temperature units
    private var selectedInputUnit = "Celsius"
    private var selectedOutputUnit = "Fahrenheit"

    // List of temperature units
    private val temperatureUnits = arrayOf("Celsius", "Fahrenheit", "Kelvin")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temp_convert) // Make sure this matches your XML filename

        // Initialize UI components
        initializeViews()

        // Setup spinners
        setupSpinners()

        // Setup button click listener
        setupButton()

        // Setup real-time conversion (optional)
        setupRealTimeConversion()
    }

    private fun initializeViews() {
        etInput = findViewById(R.id.etInput)
        etOutput = findViewById(R.id.etOutput)
        spinnerSuhuInput = findViewById(R.id.spinnerSuhuInput)
        spinnerSuhuOutput = findViewById(R.id.spinnerSuhuOutput)
        btnConvert = findViewById(R.id.btnConvert) // Make sure your Button has id="@+id/btnConvert"
        tvSaDeng = findViewById(R.id.tvSaDeng)
    }

    private fun setupSpinners() {
        // Create adapter for spinners
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, temperatureUnits)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Set adapters to both spinners
        spinnerSuhuInput.adapter = adapter
        spinnerSuhuOutput.adapter = adapter

        // Set default selections
        spinnerSuhuInput.setSelection(0) // Celsius
        spinnerSuhuOutput.setSelection(1) // Fahrenheit

        // Set listeners for spinner selections
        spinnerSuhuInput.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedInputUnit = temperatureUnits[position]
                performConversion() // Auto-convert when unit changes
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        spinnerSuhuOutput.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedOutputUnit = temperatureUnits[position]
                performConversion() // Auto-convert when unit changes
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }
    }

    private fun setupButton() {
        btnConvert.setOnClickListener {
            performConversion()
        }
    }

    private fun setupRealTimeConversion() {
        // Optional: Convert automatically as user types
        etInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not needed
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    etOutput.setText("")
                } else if (s.toString().isNotBlank() && s.toString() != ".") {
                    performConversion()
                }
            }
        })
    }

    private fun performConversion() {
        val inputText = etInput.text.toString().trim()

        // Check if input is empty
        if (inputText.isEmpty()) {
            etOutput.setText("")
            return
        }

        try {
            // Parse input value
            val inputValue = inputText.toDouble()

            // Perform conversion
            val result = convertTemperature(inputValue, selectedInputUnit, selectedOutputUnit)

            // Format result to 2 decimal places
            val formattedResult = String.format("%.2f", result)

            // Display result
            etOutput.setText(formattedResult)

        } catch (e: NumberFormatException) {
            // Show error for invalid input
            Toast.makeText(this, "Masukkan angka yang valid", Toast.LENGTH_SHORT).show()
            etOutput.setText("")
        }
    }

    private fun convertTemperature(value: Double, fromUnit: String, toUnit: String): Double {
        // If same unit, return same value
        if (fromUnit == toUnit) {
            return value
        }

        // Convert to Celsius first (as base unit)
        val valueInCelsius = when (fromUnit) {
            "Celsius" -> value
            "Fahrenheit" -> fahrenheitToCelsius(value)
            "Kelvin" -> kelvinToCelsius(value)
            else -> value
        }

        // Convert from Celsius to target unit
        return when (toUnit) {
            "Celsius" -> valueInCelsius
            "Fahrenheit" -> celsiusToFahrenheit(valueInCelsius)
            "Kelvin" -> celsiusToKelvin(valueInCelsius)
            else -> valueInCelsius
        }
    }

    // Individual conversion functions
    private fun celsiusToFahrenheit(celsius: Double): Double {
        return (celsius * 9/5) + 32
    }

    private fun fahrenheitToCelsius(fahrenheit: Double): Double {
        return (fahrenheit - 32) * 5/9
    }

    private fun celsiusToKelvin(celsius: Double): Double {
        return celsius + 273.15
    }

    private fun kelvinToCelsius(kelvin: Double): Double {
        return kelvin - 273.15
    }

    // Direct conversions for completeness
    private fun fahrenheitToKelvin(fahrenheit: Double): Double {
        return (fahrenheit - 32) * 5/9 + 273.15
    }

    private fun kelvinToFahrenheit(kelvin: Double): Double {
        return (kelvin - 273.15) * 9/5 + 32
    }
}