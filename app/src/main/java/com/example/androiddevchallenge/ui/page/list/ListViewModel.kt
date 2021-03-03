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

import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.data.db.PetDao
import com.example.androiddevchallenge.data.dto.Pet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(petDao: PetDao) : ViewModel() {

    private val genders = petDao.getGenders().map { it.map(ListFilter::GenderFilter) }

    private val sizes = petDao.getSizes().map { it.map(ListFilter::SizeFilter) }

    private val breeds = petDao.getBreeds().map { it.map(ListFilter::BreedFilter) }

    private val selectedFilters = MutableStateFlow<List<ListFilter>>(emptyList())

    val filters: Flow<List<SelectedListFilter>> = genders
        .combine(sizes) { a, b -> a + b }
        .combine(breeds) { a, b -> a + b }
        .combine(selectedFilters) { a, b -> a.map { SelectedListFilter(it, b.contains(it)) } }

    val pets: Flow<List<Pet>> = petDao.getAll()

    fun toggleFilter(filter: ListFilter) {
        selectedFilters.value.apply {
            selectedFilters.value = if (contains(filter)) {
                minus(filter)
            } else {
                plus(filter)
            }
        }
    }
}
