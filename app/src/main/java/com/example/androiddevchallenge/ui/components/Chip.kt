/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.background

@Preview("Unselected")
@Composable
fun ChipPreviewUnselected() {
    Row(
        Modifier
            .background(White)
            .padding(20.dp)
    ) {
        Chip(text = "Unselected", isSelected = false, onSelected = { })
    }
}

@Preview("Selected")
@Composable
fun ChipPreviewSelected() {
    Row(
        Modifier
            .background(White)
            .padding(20.dp)
    ) {
        Chip(text = "Selected", isSelected = true, onSelected = { })
    }
}

@Composable
fun Chip(
    text: String,
    isSelected: Boolean = false,
    onSelected: (Boolean) -> Unit,
) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .height(32.dp),
        elevation = if (isSelected) 0.dp else 4.dp,
        shape = MaterialTheme.shapes.small,
        color = if (isSelected) MaterialTheme.colors.onPrimary else lerp(MaterialTheme.colors.primary, background, 0.8f)
    ) {
        Row(
            modifier = Modifier
                .toggleable(
                    value = isSelected,
                    onValueChange = {
                        onSelected(it)
                    }
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = AnnotatedString(text),
                style = MaterialTheme.typography.body2,
                color = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}
