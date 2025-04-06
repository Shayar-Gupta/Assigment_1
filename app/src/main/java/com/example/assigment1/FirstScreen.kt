package com.example.assigment1

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.assigment1.ui.theme.downloadable_Font
import java.util.*

@SuppressLint("DefaultLocale")
@Composable
fun FirstScreen(context: Context, navController: NavHostController) {
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val birthMonth = remember { mutableStateOf("") }
    val birthDay = remember { mutableStateOf("") }
    val birthYear = remember { mutableStateOf("") }
    val showError = remember { mutableStateOf(false) }
    val invalidEmail = remember { mutableStateOf(false) }
    val invalidName = remember { mutableStateOf(false) }



    val calendar = Calendar.getInstance()

    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                birthMonth.value = String.format("%02d", month + 1)
                birthDay.value = String.format("%02d", dayOfMonth)
                birthYear.value = year.toString()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Welcome User",
            fontSize = 32.sp,
            color = Color.Black,
            style = TextStyle(fontFamily = downloadable_Font)
        )
        Text(
            text = "Please enter your details.",
            style = TextStyle(fontFamily = downloadable_Font, fontSize = 16.sp, color = Color.Gray)
        )

        Spacer(modifier = Modifier.height(36.dp))

        // Name
        Text(
            text = "Name",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 4.dp),
            style = TextStyle(fontFamily = downloadable_Font)
        )
        OutlinedTextField(
            value = name.value,
            onValueChange = {
                if (isValidName(it)) {
                    name.value = it
                    invalidName.value = false
                } else {
                    invalidName.value = true
                }
            },
            placeholder = { Text("Enter Your Name", fontSize = 16.sp, style = TextStyle(fontFamily = downloadable_Font), color = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(fontFamily = downloadable_Font, fontSize = 20.sp)
        )
        if (showError.value && name.value.isBlank()) {
            ErrorMessage()
        }
        if (invalidName.value) {
            Text(
                text = "\u26A0 Name should contain only letters",
                color = Color.Red,
                fontSize = 14.sp,
                style = TextStyle(fontFamily = downloadable_Font)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Birthday
        Text(
            text = "Birth Date",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 4.dp),
            style = TextStyle(fontFamily = downloadable_Font)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { datePickerDialog.show() },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = birthMonth.value,
                onValueChange = {birthMonth.value = it},
                enabled = false,
                placeholder = { Text("Month", fontSize = 16.sp, style = TextStyle(fontFamily = downloadable_Font), color = Color.Gray) },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp),
                textStyle = TextStyle(fontFamily = downloadable_Font, fontSize = 20.sp, color = Color.Black)
            )
            OutlinedTextField(
                value = birthDay.value,
                onValueChange = {birthDay.value = it},
                enabled = false,
                placeholder = { Text("Day", fontSize = 16.sp, style = TextStyle(fontFamily = downloadable_Font), color = Color.Gray) },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp),
                textStyle = TextStyle(fontFamily = downloadable_Font, fontSize = 20.sp, color = Color.Black)
            )
            OutlinedTextField(
                value = birthYear.value,
                onValueChange = {birthYear.value = it},
                enabled = false,
                placeholder = { Text("Year", fontSize = 16.sp, style = TextStyle(fontFamily = downloadable_Font), color = Color.Gray) },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp),
                textStyle = TextStyle(fontFamily = downloadable_Font, fontSize = 20.sp, color = Color.Black)

            )
        }
        if (showError.value && birthMonth.value.isBlank()) {
            ErrorMessage()
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Email
        Text(
            text = "Email Id",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 4.dp),
            style = TextStyle(fontFamily = downloadable_Font)
        )
        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            placeholder = { Text("Enter Your Email Id", fontSize = 16.sp, style = TextStyle(fontFamily = downloadable_Font), color = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(fontFamily = downloadable_Font, fontSize = 20.sp, color = Color.Black)
        )
        if (showError.value && email.value.isBlank()) {
            ErrorMessage()
        }
        if (invalidEmail.value) {
            Text(
                text = "\u26A0 Please enter a valid email address",
                color = Color.Red,
                fontSize = 14.sp,
                style = TextStyle(fontFamily = downloadable_Font)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                if (name.value.isBlank() || email.value.isBlank() || birthMonth.value.isBlank() || birthDay.value.isBlank() || birthYear.value.isBlank()) {
                    showError.value = true
                    invalidEmail.value = false
                } else if (!isValidEmail(email.value)) {
                    showError.value = false
                    invalidEmail.value = true
                } else {
                    showError.value = false
                    invalidEmail.value = false
                    val birthday = "${birthMonth.value} ${birthDay.value}, ${birthYear.value}"
                    val userData = UserData(name.value, birthday, email.value)

                    UserStore.users.add(userData)

                    navController.navigate("resultScreen")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF001F54),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Submit", fontSize = 20.sp, style = TextStyle(fontFamily = downloadable_Font))
        }
    }
}

@Composable
fun ErrorMessage() {
    Row(
        modifier = Modifier.padding(top = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "\u26A0 Kindly fill details", // Unicode for warning sign
            color = Color.Red,
            fontSize = 14.sp,
            style = TextStyle(fontFamily = downloadable_Font)
        )
    }
}

fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isValidName(input: String): Boolean {
    return input.matches(Regex("^[a-zA-Z ]*$"))
}
