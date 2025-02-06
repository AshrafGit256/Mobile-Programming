package com.example.calculator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val buttonList = listOf(
    "⌫", "(", ")", "÷",
    "7", "8", "9", "*",
    "4", "5", "6", "+",
    "1", "2", "3", "-",
    "AC", "0", ".", "="
)

@Composable
fun Calculator(modifier: Modifier = Modifier, viewModel: CalculatorViewModel) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display Section
        CalculatorDisplay(viewModel = viewModel, modifier = Modifier.weight(1f))

        Spacer(modifier = Modifier.height(16.dp)) // Adds spacing

        // Buttons Section
        CalculatorButtons(viewModel = viewModel)
    }
}

@Composable
fun CalculatorDisplay(viewModel: CalculatorViewModel, modifier: Modifier = Modifier) {
    val equationText = viewModel.equationText.observeAsState()
    val resultText = viewModel.resultText.observeAsState()

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = equationText.value ?: "",
            style = TextStyle(
                fontSize = getDynamicFontSize(equationText.value ?: ""),
                textAlign = TextAlign.End
            ),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(end = 16.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = resultText.value ?: "",
            style = TextStyle(
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End,
                color = Color(0xFFA87D4B) // Different color for visibility
            ),
            maxLines = 1,
            modifier = Modifier.padding(end = 16.dp)
        )
    }
}

@Composable
fun CalculatorButtons(viewModel: CalculatorViewModel) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(buttonList) {
            CalculatorButton(btn = it, onClick = {
                viewModel.onButtonClick(it)
            })
        }
    }
}

@Composable
fun CalculatorButton(btn: String, onClick: () -> Unit) {
    Box(modifier = Modifier.padding(6.dp)) {
        FloatingActionButton(
            onClick = onClick,
            modifier = Modifier.size(70.dp),
            shape = RoundedCornerShape(12.dp),
            contentColor = Color.White,
            containerColor = getColor(btn)
        ) {
            Text(text = btn, fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }
    }
}

fun getColor(btn: String): Color {
    return when (btn) {
        "⌫", "AC" -> Color(0xFFD32F2F) // Red for clearing buttons
        "(", ")" -> Color(0xFF33916E)  // Green for brackets
        "÷", "*", "+", "-", "=" -> Color(0xFFA87D4B) // Warm brown for operators
        "." -> Color(0xFF548A98)  // Blue for decimal
        else -> Color(0xFF5A5E60) // Gray for numbers
    }
}

fun getDynamicFontSize(text: String): androidx.compose.ui.unit.TextUnit {
    return when {
        text.length < 10 -> 60.sp
        text.length < 13 -> 47.sp
        text.length < 17 -> 37.sp
        text.length < 25 -> 26.sp
        else -> 24.sp
    }
}
