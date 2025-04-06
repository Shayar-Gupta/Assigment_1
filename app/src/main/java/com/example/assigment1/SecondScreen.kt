package com.example.assigment1

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assigment1.ui.theme.darkBlue
import com.example.assigment1.ui.theme.downloadable_Font


@Composable
fun SecondScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Registration Successful",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            style = TextStyle(fontFamily = downloadable_Font)
        )

        Text(
            text = "Your Details are Submitted successfully",
            color = Color.Gray,
            style = TextStyle(fontFamily = downloadable_Font)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Card(
            border = BorderStroke(1.dp, Color.Gray),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(8.dp)) {

                Row(Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                    TableCell("Name", true, modifier = Modifier.weight(1f))
                    TableCell("Birthday", true, modifier = Modifier.weight(1f))
                    TableCell("Email Id", true, modifier = Modifier.weight(1f))
                }


                HorizontalDivider(color = Color.Gray, thickness = 1.dp)

                // Table Rows
                UserStore.users.forEach { user ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                    ) {
                        TableCell(user.name, false, modifier = Modifier.weight(1f))
                        TableCell(user.birthday, false, modifier = Modifier.weight(1f))
                        TableCell(user.email.take(25) + if (user.email.length > 25) "..." else "", false, modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun TableCell(text: String, isHeader: Boolean = false, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier
            .padding(8.dp),
        color = if (isHeader) darkBlue else Color.DarkGray,
        style = TextStyle(fontFamily = downloadable_Font, fontSize = 16.sp)
    )
}

