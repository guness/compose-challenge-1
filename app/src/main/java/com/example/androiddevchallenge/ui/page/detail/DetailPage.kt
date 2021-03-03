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
package com.example.androiddevchallenge.ui.page.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.outlinedButtonColors
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Message
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.dto.Pet
import com.example.androiddevchallenge.data.dto.resource
import com.example.androiddevchallenge.utils.asDrawableRes

@Composable
fun DetailPage(pet: Pet, viewModel: DetailViewModel = viewModel()) {
    Box(Modifier.fillMaxSize()) {
        ConstraintLayout {

            val (image, card, spacer, name, ageLabel, age, genderLabel, gender, description, message, adopt) = createRefs()

            Image(
                painter = painterResource(pet.image.asDrawableRes(LocalContext.current)),
                contentDescription = pet.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                    },
                alignment = Alignment.Center
            )
            Spacer(
                modifier = Modifier
                    .height(24.dp)
                    .fillMaxWidth()
                    .constrainAs(spacer) {
                        bottom.linkTo(image.bottom)
                    }
            )
            Card(
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(card) {
                        bottom.linkTo(parent.bottom)
                        top.linkTo(spacer.top)
                    },
            ) {
                Spacer(
                    modifier = Modifier.fillMaxSize(0.7f)
                )
            }
            Text(
                text = pet.name,
                modifier = Modifier.constrainAs(name) {
                    top.linkTo(card.top, margin = 24.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                },
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.h5
            )

            Text(
                text = stringResource(id = R.string.age).toUpperCase(),
                modifier = Modifier.constrainAs(ageLabel) {
                    top.linkTo(name.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                },
                color = MaterialTheme.colors.onPrimary.copy(alpha = 0.8f),
                style = MaterialTheme.typography.overline
            )

            Text(
                text = stringResource(id = R.string.gender).toUpperCase(),
                modifier = Modifier.constrainAs(genderLabel) {
                    top.linkTo(name.bottom, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                },
                color = MaterialTheme.colors.onPrimary.copy(alpha = 0.8f),
                style = MaterialTheme.typography.overline
            )

            Text(
                text = stringResource(id = R.string.x_months, pet.age),
                modifier = Modifier.constrainAs(age) {
                    top.linkTo(ageLabel.bottom, margin = 4.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                },
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.body2
            )

            Text(
                text = stringResource(id = pet.gender?.resource ?: 0),
                modifier = Modifier.constrainAs(gender) {
                    top.linkTo(genderLabel.bottom, margin = 4.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                },
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.body2
            )

            Text(
                text = pet.description ?: "-",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .constrainAs(description) {
                        top.linkTo(age.bottom, margin = 24.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.body1
            )

            Button(
                onClick = { },
                modifier = Modifier.constrainAs(message) {
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
            ) {
                Icon(Icons.Filled.Message, "message")
            }

            OutlinedButton(
                onClick = { },
                modifier = Modifier
                    .constrainAs(adopt) {
                        bottom.linkTo(parent.bottom, margin = 16.dp)
                        end.linkTo(parent.end, margin = 16.dp)
                        start.linkTo(message.end, margin = 16.dp)
                    }
                    .fillMaxWidth(0.6f),
                colors = outlinedButtonColors(
                    backgroundColor = MaterialTheme.colors.onPrimary,
                    contentColor = MaterialTheme.colors.primary
                )
            ) {
                Text(
                    text = stringResource(id = R.string.adopt_me)
                )
            }
        }
    }
}
