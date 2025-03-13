package com.ung.mylibrary

import android.graphics.Color
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text


@Composable
fun AccountRow(
    modifier: Modifier = Modifier,
    name: String,
    number: Int,
) {

    Row {
        Text(text = name)
        Text(text = number.toString())
    }


}
