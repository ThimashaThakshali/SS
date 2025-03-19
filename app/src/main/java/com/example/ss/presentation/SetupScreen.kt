package com.example.ss.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ss.R

@Composable
fun SetupScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()), // Enables scrolling
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Set Up Screen")

        Spacer(modifier = Modifier.height(16.dp))

        SetupOption(
            iconRes = R.drawable.alert_icon,
            text = "Add Alerts",
            onClick = { /* Navigate to Add Alerts Screen */ }
        )

        SetupOption(
            iconRes = R.drawable.add_contact_icon,
            text = "Add Contacts",
            onClick = { /* Navigate to Add Contacts Screen */ }
        )

        SetupOption(
            iconRes = R.drawable.safe_zone_icon,
            text = "Add Safe Zone",
            onClick = { /* Navigate to Add Safe Zone Screen */ }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Continue",
            modifier = Modifier.clickable {
                navController.navigate("mainScreen") {
                    popUpTo("setupScreen") { inclusive = true }
                }
            }
        )
    }
}

@Composable
fun SetupOption(iconRes: Int, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = text,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun SetupScreenPreview() {
    SetupScreen(navController = rememberNavController())
}
