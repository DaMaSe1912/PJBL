package com.damay.aplikasipertama

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ConstraintActivity : AppCompatActivity() {

    // Step 3.1: Declare variables for all views
    private lateinit var etPreview: EditText

    // Number buttons
    private lateinit var btn0: Button
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button
    private lateinit var btn5: Button
    private lateinit var btn6: Button
    private lateinit var btn7: Button
    private lateinit var btn8: Button
    private lateinit var btn9: Button

    // Operation buttons
    private lateinit var btnC: Button
    private lateinit var btnDel: Button
    private lateinit var btnTambah: Button
    private lateinit var btnKurang: Button
    private lateinit var btnKali: Button
    private lateinit var btnBagi: Button
    private lateinit var btnKoma: Button
    private lateinit var btnSaDeng: Button

    // Step 3.2: Variables to store calculator data
    private var currentInput = StringBuilder()
    private var currentOperator = ""
    private var firstOperand = 0.0
    private var shouldResetInput = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Step 3.3: Set the layout
        setContentView(R.layout.activity_main)

        // Step 3.4: Initialize all views
        initializeViews()

        // Step 3.5: Set up button click listeners
        setupClickListeners()
    }

    // Step 4: Initialize Views Method
    private fun initializeViews() {
        // Initialize the display
        etPreview = findViewById(R.id.etPreview)

        // Initialize number buttons
        btn0 = findViewById(R.id.btn0)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)
        btn6 = findViewById(R.id.btn6)
        btn7 = findViewById(R.id.btn7)
        btn8 = findViewById(R.id.btn8)
        btn9 = findViewById(R.id.btn9)

        // Initialize operation buttons
        btnC = findViewById(R.id.btnC)
        btnDel = findViewById(R.id.btnDel)
        btnTambah = findViewById(R.id.btnTambah)
        btnKurang = findViewById(R.id.btnKurang)
        btnKali = findViewById(R.id.btnKali)
        btnBagi = findViewById(R.id.btnBagi)
        btnKoma = findViewById(R.id.btnKoma)
        btnSaDeng = findViewById(R.id.btnSaDeng)
    }

    // Step 5: Setup Click Listeners
    private fun setupClickListeners() {
        // Number buttons
        btn0.setOnClickListener { appendNumber("0") }
        btn1.setOnClickListener { appendNumber("1") }
        btn2.setOnClickListener { appendNumber("2") }
        btn3.setOnClickListener { appendNumber("3") }
        btn4.setOnClickListener { appendNumber("4") }
        btn5.setOnClickListener { appendNumber("5") }
        btn6.setOnClickListener { appendNumber("6") }
        btn7.setOnClickListener { appendNumber("7") }
        btn8.setOnClickListener { appendNumber("8") }
        btn9.setOnClickListener { appendNumber("9") }

        // Operation buttons
        btnC.setOnClickListener { clearAll() }
        btnDel.setOnClickListener { deleteLast() }
        btnTambah.setOnClickListener { setOperator("+") }
        btnKurang.setOnClickListener { setOperator("-") }
        btnKali.setOnClickListener { setOperator("*") }
        btnBagi.setOnClickListener { setOperator("/") }
        btnKoma.setOnClickListener { appendDecimal() }
        btnSaDeng.setOnClickListener { calculateResult() }
    }

    // Step 6: Number Input Function
    private fun appendNumber(number: String) {
        if (shouldResetInput) {
            currentInput.clear()
            shouldResetInput = false
        }

        // Prevent multiple leading zeros
        if (currentInput.toString() == "0") {
            currentInput.clear()
        }

        currentInput.append(number)
        updateDisplay()
    }

    // Step 7: Decimal Point Function
    private fun appendDecimal() {
        if (shouldResetInput) {
            currentInput.clear()
            shouldResetInput = false
        }

        // If empty, start with "0."
        if (currentInput.isEmpty()) {
            currentInput.append("0")
        }

        // Add decimal point only if there isn't one already
        if (!currentInput.contains(".")) {
            currentInput.append(".")
            updateDisplay()
        }
    }

    // Step 8: Operator Function
    private fun setOperator(operator: String) {
        if (currentInput.isNotEmpty()) {
            // If we already have an operator, calculate the result first
            if (currentOperator.isNotEmpty()) {
                calculateResult()
            }

            // Store the first number and operator
            firstOperand = currentInput.toString().toDouble()
            currentOperator = operator
            shouldResetInput = true
        }
    }

    // Step 9: Calculation Function
    private fun calculateResult() {
        if (currentInput.isNotEmpty() && currentOperator.isNotEmpty()) {
            val secondOperand = currentInput.toString().toDouble()
            var result = 0.0

            try {
                // Perform the calculation based on the operator
                result = when (currentOperator) {
                    "+" -> firstOperand + secondOperand
                    "-" -> firstOperand - secondOperand
                    "*" -> firstOperand * secondOperand
                    "/" -> {
                        // Check for division by zero
                        if (secondOperand == 0.0) {
                            throw ArithmeticException("Division by zero")
                        }
                        firstOperand / secondOperand
                    }
                    else -> 0.0
                }

                // Format the result (remove .0 if it's a whole number)
                val formattedResult = if (result % 1 == 0.0) {
                    result.toInt().toString()
                } else {
                    result.toString()
                }

                // Display the result
                currentInput.clear().append(formattedResult)
                updateDisplay()

            } catch (e: ArithmeticException) {
                // Handle division by zero error
                currentInput.clear().append("Error")
                updateDisplay()
            }

            // Reset operator for next calculation
            currentOperator = ""
            shouldResetInput = true
        }
    }

    // Step 10: Clear Function
    private fun clearAll() {
        currentInput.clear()
        currentOperator = ""
        firstOperand = 0.0
        shouldResetInput = false
        etPreview.text.clear()
        etPreview.hint = "01234"
    }

    // Step 11: Delete Function
    private fun deleteLast() {
        if (currentInput.isNotEmpty()) {
            currentInput.deleteCharAt(currentInput.length - 1)
            updateDisplay()
        }

        // If nothing left, show hint
        if (currentInput.isEmpty()) {
            etPreview.hint = "01234"
        }
    }

    // Step 12: Update Display Function
    private fun updateDisplay() {
        etPreview.setText(currentInput.toString())
    }

    // Step 13: Save State (Optional - for screen rotation)
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("currentInput", currentInput.toString())
        outState.putString("currentOperator", currentOperator)
        outState.putDouble("firstOperand", firstOperand)
        outState.putBoolean("shouldResetInput", shouldResetInput)
    }

    // Step 14: Restore State (Optional - for screen rotation)
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        currentInput.clear().append(savedInstanceState.getString("currentInput", ""))
        currentOperator = savedInstanceState.getString("currentOperator", "")
        firstOperand = savedInstanceState.getDouble("firstOperand", 0.0)
        shouldResetInput = savedInstanceState.getBoolean("shouldResetInput", false)
        updateDisplay()
    }
}