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
package com.example.androiddevchallenge.ui.page.list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.MainViewModel
import com.example.androiddevchallenge.data.dto.Pet
import com.example.androiddevchallenge.data.dto.resource
import com.example.androiddevchallenge.ui.components.Chip
import com.example.androiddevchallenge.ui.components.PetItem

@Composable
fun ListPage(viewModel: ListViewModel = viewModel(), mainViewModel: MainViewModel = viewModel()) {

    val filters: List<SelectedListFilter> by viewModel.filters.collectAsState(initial = emptyList())

    val pets: List<Pet> by viewModel.pets.collectAsState(emptyList())

    LazyColumn(
        content = {
            item {
                LazyRow(
                    content = {
                        item { Spacer(modifier = Modifier.width(8.dp)) }
                        items(
                            filters.count(),
                            key = {
                                filters[it].filter.key
                            }
                        ) {
                            val filter = filters[it].filter
                            val text = when (filter) {
                                is ListFilter.BreedFilter -> filter.breed
                                is ListFilter.GenderFilter -> stringResource(id = filter.gender.resource)
                                is ListFilter.SizeFilter -> stringResource(id = filter.size.resource)
                            }
                            Chip(
                                text = text, filters[it].selected,
                                onSelected = {
                                    viewModel.toggleFilter(filter)
                                }
                            )
                        }
                        item { Spacer(modifier = Modifier.width(8.dp)) }
                    },
                    modifier = Modifier.padding(top = 12.dp, bottom = 4.dp)
                )
            }
            items(pets.count(), key = { pets[it].uid }) {
                PetItem(pets[it], onClick = mainViewModel::selectPet)
            }
        }
    )
}
