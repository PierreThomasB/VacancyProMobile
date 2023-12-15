package com.project.vacancypromobile.ui.screens.composent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ColoredCircleWithInitials(color: Color, initials: String) {
    Box(
        modifier = Modifier
            .size(70.dp)
            .clip(CircleShape)
            .background(color)
            .padding(4.dp)
    ) {
        Text(
            text = initials,
            color = contentColorFor(color),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun ColoredCircleWithInitialsPreview() {
    ColoredCircleWithInitials(
        color = Color.Red,
        initials = "AB"
    )
}