package com.example.question_1_group

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.question_1_group.ui.theme.Question1GroupTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Question1GroupTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TempSlider(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    @Composable
    fun TempSlider(modifier: Modifier = Modifier) {
        var celsius by remember { mutableStateOf(0f) }
        var fahrenheit by remember { mutableStateOf(32f) }

        fun celsiusToFahrenheit(celsius: Float): Float {
            return (celsius * 9 / 5) + 32
        }

        fun fahrenheitToCelsius(fahrenheit: Float): Float {
            return (fahrenheit - 32) * 5 / 9
        }

        fun updateFahrenheitFromCelsius(celsiusValue: Float) {
            fahrenheit = celsiusToFahrenheit(celsiusValue)
        }

        fun updateCelsiusFromFahrenheit(fahrenheitValue: Float) {
            celsius = fahrenheitToCelsius(fahrenheitValue)

        }

        val interestingMessage = if (celsius <= 20f) "I wish it were warmer." else "I wish it were colder."

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Celsius: ${celsius.toInt()}ºC",
                fontSize = 20.sp,
            )

            Slider(
                value = celsius,
                onValueChange = {
                    celsius = it
                    updateFahrenheitFromCelsius(it)
                },
                valueRange = 0f..100f,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Fahrenheit: ${fahrenheit.toInt()}ºF",
                fontSize = 20.sp,
            )

            Slider(
                value = fahrenheit,
                onValueChange = {
                    fahrenheit = it
                    updateCelsiusFromFahrenheit(it)
                },
                onValueChangeFinished = {
                    if (fahrenheit < 32f) {
                        fahrenheit = 32f
                        updateCelsiusFromFahrenheit(32f)
                    }
                },
                valueRange = 0f..212f,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = interestingMessage,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        Question1GroupTheme {
            TempSlider()
        }
    }
}