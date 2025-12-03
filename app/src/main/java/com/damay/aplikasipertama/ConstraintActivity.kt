package com.damay.aplikasipertama

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ConstraintActivity : AppCompatActivity() {

    // UI Components
    private lateinit var etPreview: TextView
    private lateinit var tvHistory: TextView

    // Calculator state variables
    private var currentInput = StringBuilder()
    private var currentOperator = ""
    private var previousValue = ""
    private var isNewOperation = true
    private var calculationHistory = mutableListOf<String>()

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
    private lateinit var btnBagi: Button
    private lateinit var btnKali: Button
    private lateinit var btnKurang: Button
    private lateinit var btnTambah: Button
    private lateinit var btnKoma: Button
    private lateinit var btnSaDeng: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint)

        initializeViews()
        setupClickListeners()
        updateDisplay()
    }

    private fun initializeViews() {
        etPreview = findViewById(R.id.etPreview)

        // Create history TextView programmatically
        tvHistory = TextView(this).apply {
            id = View.generateViewId()
            layoutParams = androidx.constraintlayout.widget.ConstraintLayout.LayoutParams(
                androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
                androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(16, 32, 16, 8)
                topToTop = R.id.main
                startToStart = R.id.main
                endToEnd = R.id.main
                bottomToTop = R.id.etPreview
            }
            textSize = 14f
            textAlignment = TextView.TEXT_ALIGNMENT_TEXT_END
            setTextColor(resources.getColor(android.R.color.darker_gray, theme))
            maxLines = 3
            ellipsize = android.text.TextUtils.TruncateAt.END
        }

        // Add history TextView to layout
        val mainLayout = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.main)
        mainLayout.addView(tvHistory)

        // Initialize buttons
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

        btnC = findViewById(R.id.btnC)
        btnDel = findViewById(R.id.btnDel)
        btnBagi = findViewById(R.id.btnBagi)
        btnKali = findViewById(R.id.btnKali)
        btnKurang = findViewById(R.id.btnKurang)
        btnTambah = findViewById(R.id.btnTambah)
        btnKoma = findViewById(R.id.btnKoma)
        btnSaDeng = findViewById(R.id.btnSaDeng)
    }

    private fun setupClickListeners() {
        // Number buttons
        val numberButtons = listOf(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)
        numberButtons.forEach { button ->
            button.setOnClickListener {
                onNumberClick(button.text.toString())
            }
        }

        // Operation buttons
        btnTambah.setOnClickListener { onOperatorClick("+") }
        btnKurang.setOnClickListener { onOperatorClick("-") }
        btnKali.setOnClickListener { onOperatorClick("*") }
        btnBagi.setOnClickListener { onOperatorClick("/") }
        btnKoma.setOnClickListener { onDecimalClick() }

        // Function buttons
        btnC.setOnClickListener { onClearClick() }
        btnDel.setOnClickListener { onDeleteClick() }
        btnSaDeng.setOnClickListener { onEqualsClick() }
    }

    private fun onNumberClick(number: String) {
        if (isNewOperation) {
            currentInput.clear()
            isNewOperation = false
        }

        // Prevent leading zeros
        if (currentInput.toString() == "0") {
            currentInput.clear()
        }

        currentInput.append(number)
        updateDisplay()
    }

    private fun onOperatorClick(operator: String) {
        if (currentInput.isNotEmpty()) {
            if (previousValue.isNotEmpty() && currentOperator.isNotEmpty()) {
                // Calculate previous operation first
                performCalculation()
            }

            previousValue = currentInput.toString()
            currentOperator = operator
            currentInput.clear()

            // Update history display with the ongoing operation
            updateHistoryDisplay("$previousValue $currentOperator")
        } else if (previousValue.isNotEmpty()) {
            // Allow changing the operator
            currentOperator = operator
            updateHistoryDisplay("$previousValue $currentOperator")
        }

        updateDisplay()
    }

    private fun onDecimalClick() {
        if (isNewOperation) {
            currentInput.clear()
            currentInput.append("0")
            isNewOperation = false
        }

        if (!currentInput.contains(".")) {
            if (currentInput.isEmpty()) {
                currentInput.append("0")
            }
            currentInput.append(".")
            updateDisplay()
        }
    }

    private fun onClearClick() {
        currentInput.clear()
        previousValue = ""
        currentOperator = ""
        isNewOperation = true
        updateDisplay()
        tvHistory.text = ""
    }

    private fun onDeleteClick() {
        if (currentInput.isNotEmpty()) {
            currentInput.deleteCharAt(currentInput.length - 1)
            updateDisplay()
        } else if (currentOperator.isNotEmpty()) {
            // If deleting after an operator, revert to previous value
            currentInput.append(previousValue)
            previousValue = ""
            currentOperator = ""
            updateDisplay()
        }
    }

    private fun onEqualsClick() {
        if (previousValue.isNotEmpty() && currentOperator.isNotEmpty() && currentInput.isNotEmpty()) {
            performCalculation()

            // Add to history
            addToHistory()

            // Reset for next operation
            isNewOperation = true
            updateDisplay()
        }
    }

    private fun performCalculation() {
        try {
            val firstNum = previousValue.toDouble()
            val secondNum = currentInput.toString().toDouble()
            var result = 0.0

            when (currentOperator) {
                "+" -> result = firstNum + secondNum
                "-" -> result = firstNum - secondNum
                "*" -> result = firstNum * secondNum
                "/" -> {
                    if (secondNum != 0.0) {
                        result = firstNum / secondNum
                    } else {
                        currentInput.clear()
                        currentInput.append("Error")
                        updateDisplay()
                        return
                    }
                }
            }

            // Format result to remove unnecessary decimals
            val formattedResult = formatResult(result)

            // Update history with full calculation
            val historyEntry = "$previousValue $currentOperator $secondNum = $formattedResult"
            updateHistoryDisplay(historyEntry, isFinal = true)

            // Set result as current input for chain calculations
            currentInput.clear()
            currentInput.append(formattedResult)
            previousValue = formattedResult

        } catch (e: NumberFormatException) {
            currentInput.clear()
            currentInput.append("Error")
        }
    }

    private fun formatResult(result: Double): String {
        return if (result == result.toLong().toDouble()) {
            // If it's a whole number, display without decimal
            result.toLong().toString()
        } else {
            // Format to remove unnecessary trailing zeros
            val df = DecimalFormat("#.########")
            df.format(result)
        }
    }

    private fun addToHistory() {
        if (previousValue.isNotEmpty() && currentOperator.isNotEmpty() && currentInput.isNotEmpty()) {
            val historyEntry = "$previousValue $currentOperator ${currentInput} = ${currentInput}"
            calculationHistory.add(historyEntry)

            // Keep only last 10 calculations in memory
            if (calculationHistory.size > 10) {
                calculationHistory.removeAt(0)
            }
        }
    }

    private fun updateHistoryDisplay(operation: String, isFinal: Boolean = false) {
        if (isFinal) {
            // For final calculations, show in history
            tvHistory.text = operation
        } else {
            // For ongoing operations, show the current operation
            tvHistory.text = operation
        }
    }

    private fun updateDisplay() {
        val displayText = if (currentInput.isEmpty()) {
            if (previousValue.isNotEmpty() && currentOperator.isNotEmpty()) {
                // Show previous value and operator when waiting for second number
                "$previousValue $currentOperator"
            } else {
                "0"
            }
        } else {
            // Format number with thousand separators
            formatNumberWithCommas(currentInput.toString())
        }

        etPreview.text = displayText
    }

    private fun formatNumberWithCommas(number: String): String {
        return try {
            // Remove existing commas and parse
            val cleanNumber = number.replace(",", "")

            // Check if it contains decimal point
            if (cleanNumber.contains(".")) {
                val parts = cleanNumber.split(".")
                val integerPart = parts[0].toLongOrNull()
                val decimalPart = parts.getOrNull(1) ?: ""

                if (integerPart != null) {
                    val formattedInteger = DecimalFormat("#,###").format(integerPart)
                    if (decimalPart.isNotEmpty()) {
                        "$formattedInteger.$decimalPart"
                    } else {
                        formattedInteger
                    }
                } else {
                    cleanNumber
                }
            } else {
                // Whole number
                val num = cleanNumber.toLongOrNull()
                if (num != null) {
                    DecimalFormat("#,###").format(num)
                } else {
                    cleanNumber
                }
            }
        } catch (e: Exception) {
            number
        }
    }

    // Extension function to check if StringBuilder contains a string
    private fun StringBuilder.contains(charSequence: CharSequence): Boolean {
        return this.contains(charSequence)
    }
}