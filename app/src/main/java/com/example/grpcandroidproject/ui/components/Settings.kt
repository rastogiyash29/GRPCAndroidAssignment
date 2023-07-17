package com.example.grpcandroidproject.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsList() {
    val settings= listOf(
        "Account",
        "Notifications",
        "Edit Profile",
        "Change Password",
        "Logout"
    )
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(settings) { setting ->
            SettingItem(setting)
        }
    }
}

@Composable
fun SettingItem(setting: String) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {  }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = setting)
        }
        Divider()
    }
}