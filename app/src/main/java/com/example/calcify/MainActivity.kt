package com.example.calcify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calcify.ui.theme.Brown
import com.example.calcify.ui.theme.Brownie
import com.example.calcify.ui.theme.CalcifyTheme
import com.example.calcify.ui.theme.Chalky
import com.example.calcify.ui.theme.lessB

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalcifyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Chalky
                ) {
                    Calculator()
                }
            }
        }
    }
}

@Composable
fun Calculator() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.calcify),
            modifier = Modifier.height(100.dp),
            contentDescription = "calcify icon")
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "CALCIFY",
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            color = Brownie
        )
        Spacer(modifier = Modifier.height(30.dp))

        var numbers by rememberSaveable { mutableStateOf("") }
        OutlinedTextField(
            value = numbers,
            onValueChange = { numbers = it },
            modifier = Modifier.height(150.dp),
            readOnly = true,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = lessB,
                focusedContainerColor = lessB,
                focusedBorderColor = Brown,
                unfocusedBorderColor = Brown
            )
        )
        Spacer(modifier = Modifier.height(30.dp))

        val buttonRows = listOf(
            listOf("Clear", "Delete"),
            listOf("9", "8", "7", "+"),
            listOf("6", "5", "4", "-"),
            listOf("3", "2", "1", "x"),
            listOf("/", "0", "=")
        )
        buttonRows.forEach { rowItems ->
            Row {
                rowItems.forEach { item ->
                    Button(
                        colors = ButtonDefaults.buttonColors(Brown),
                        onClick = {
                            when (item) {
                                "Clear" -> numbers = ""
                                "Delete" -> if (numbers.isNotEmpty())numbers = numbers.dropLast(1)
                                "=" -> numbers = calculate(numbers)
                                else -> numbers += item
                            }
                    }
                    ) {
                        Text(text = item)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

fun calculate(numbers: String): String {
    var result = ""
    if (numbers.contains("+")){
        var parts = numbers.split("+")
        result = (parts[0].toInt() + parts[1].toInt()).toString()
    }
    if (numbers.contains("-")){
        var parts = numbers.split("-")
        result = (parts[0].toInt() - parts[1].toInt()).toString()
    }
    if (numbers.contains("x")){
        var parts = numbers.split("x")
        result = (parts[0].toInt() * parts[1].toInt()).toString()
    }
    if (numbers.contains("/")){
        var parts = numbers.split("/")
        result = (parts[0].toDouble() / parts[1].toDouble()).toString()
    }

    return result
}


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        CalcifyTheme {
            Calculator()
        }
    }