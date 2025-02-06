package com.example.calculator

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable

class CalculatorViewModel : ViewModel() {

    private val _equationText = MutableLiveData("")
    val equationText: LiveData<String> = _equationText

    private val _resultText = MutableLiveData("0")
    val resultText: LiveData<String> = _resultText

    private var _lastValidResult: String = "0"  // Store last valid result

    fun onButtonClick(btn: String) {
        Log.i("Clicked Button", btn)

        _equationText.value?.let { currentText ->
            when (btn) {
                "AC" -> {
                    _equationText.value = ""
                    _resultText.value = "0"
                    _lastValidResult = "0"
                    return
                }

                "⌫" -> {
                    if (currentText.isNotEmpty()) {
                        _equationText.value = currentText.substring(0, currentText.length - 1)
                    }
                    return
                }

                "=" -> {
                    if (!isValidExpression(currentText)) {
                        _resultText.value = "Error"  // Show error only when "=" is pressed
                    } else {
                        _resultText.value = calculateResult(currentText)
                        _lastValidResult = _resultText.value!!  // Store the valid result
                        _equationText.value = _resultText.value  // Update equation with result
                    }
                    return
                }
            }

            // Prevent too long input
            if (currentText.length >= 25) return

            // Append the new button press
            _equationText.value = currentText + btn

            // Check if the expression is valid so far
            if (isValidExpression(_equationText.value.toString())) {
                try {
                    val result = calculateResult(_equationText.value.toString())
                    _resultText.value = result
                    _lastValidResult = result  // Store the latest valid result
                } catch (_: Exception) {
                    _resultText.value = _lastValidResult  // Keep showing last valid result
                }
            } else {
                _resultText.value = _lastValidResult  // Keep the last valid result visible
            }
        }
    }

    fun calculateResult(equation: String): String {
        return try {
            val context: Context = Context.enter()
            context.optimizationLevel = -1
            val scriptable: Scriptable = context.initStandardObjects()

            val formattedEquation = equation.replace("÷", "/")  // Replace division symbol

            val finalResult = context.evaluateString(scriptable, formattedEquation, "Javascript", 1, null).toString()
            val resultAsDouble = finalResult.toDoubleOrNull() ?: return "Error"

            val resultString = if (resultAsDouble % 1 == 0.0) {
                resultAsDouble.toLong().toString()  // Whole number as integer
            } else {
                String.format("%.9f", resultAsDouble).trimEnd('0').trimEnd('.')  // Remove trailing zeros
            }

            return if (resultString.length > 16) {
                String.format("%.9e", resultAsDouble)  // Convert long results to scientific notation
            } else {
                resultString
            }
        } catch (e: Exception) {
            "Error"  // Only show error when "=" is pressed
        } finally {
            Context.exit()
        }
    }

    fun isValidExpression(equation: String): Boolean {
        val regex = Regex("""\d+(\.\d+)?\s*[-+*/÷]\s*\d+(\.\d+)?""")
        return regex.containsMatchIn(equation)
    }
}
