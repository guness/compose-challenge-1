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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.dto.Gender
import com.example.androiddevchallenge.data.dto.Pet
import com.example.androiddevchallenge.data.dto.PetSize
import com.example.androiddevchallenge.data.dto.resource
import com.example.androiddevchallenge.ui.theme.filterColor
import com.example.androiddevchallenge.utils.asDrawableRes

@Preview
@Composable
fun PetCardSelected() {
    Row(
        Modifier
            .background(Color.White)
            .padding(20.dp)
    ) {
        PetItem(
            pet = Pet(
                uid = 2,
                name = "Akbas",
                image = "image1.jpg",
                breed = "Akbas Dog",
                size = PetSize.LARGE,
                age = 7,
                likes = 134,
                liked = true,
                gender = Gender.MALE,
                description = "Lipsum Description"
            ),
            onClick = { }
        )
    }
}

@Composable
fun PetItem(pet: Pet, onClick: (Pet) -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick(pet) }
    ) {
        Image(
            painter = painterResource(pet.image.asDrawableRes(LocalContext.current)),
            contentDescription = pet.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.Center
        )
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(filterColor)
        )
        ConstraintLayout {
            val (icon, gender, age, breed) = createRefs()
            Box(
                modifier = Modifier
                    .size(24.dp, 24.dp)
                    .constrainAs(icon) {
                        top.linkTo(parent.top, margin = 16.dp)
                        end.linkTo(parent.end, margin = 24.dp)
                    }

            ) {
                Icon(
                    painter = painterResource(id = if (pet.liked) R.drawable.ic_bookmarked else R.drawable.ic_bookmark),
                    contentDescription = "Bookmark",
                    tint = Color.White
                )
            }
            Text(
                text = stringResource(id = pet.gender?.resource ?: 0),
                modifier = Modifier.constrainAs(gender) {
                    end.linkTo(parent.end, margin = 16.dp)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                },
                color = MaterialTheme.colors.surface,
                style = MaterialTheme.typography.body1
            )
            Text(
                text = stringResource(id = R.string.x_months, pet.age),
                modifier = Modifier.constrainAs(age) {
                    start.linkTo(parent.start, margin = 16.dp)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                },
                color = MaterialTheme.colors.surface,
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                text = pet.breed,
                modifier = Modifier.constrainAs(breed) {
                    start.linkTo(parent.start, margin = 16.dp)
                    bottom.linkTo(age.top, margin = 2.dp)
                },
                color = MaterialTheme.colors.surface,
                style = MaterialTheme.typography.h6
            )
        }
    }
}
