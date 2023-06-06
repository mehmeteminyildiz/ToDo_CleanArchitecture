package com.my.todoclean.feature_todo.presentation.todos.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.my.todoclean.ui.theme.SelectedRB
import com.my.todoclean.ui.theme.TextColor
import com.my.todoclean.ui.theme.UnselectedRB

@Composable
fun DefaultRadioButton(
    text: String, selected: Boolean, onSelect: () -> Unit, modifier: Modifier
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected, onClick = onSelect, colors = RadioButtonDefaults.colors(
                selectedColor = SelectedRB,
                unselectedColor = UnselectedRB
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, style = MaterialTheme.typography.body1, color = TextColor)

    }
}