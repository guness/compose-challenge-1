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
package com.example.androiddevchallenge.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.TypeConverters
import com.example.androiddevchallenge.data.dto.Gender
import com.example.androiddevchallenge.data.dto.Pet
import com.example.androiddevchallenge.data.dto.PetSize
import kotlinx.coroutines.flow.Flow

@Dao
interface PetDao {
    @Query("SELECT * FROM ${AppDatabase.TABLE_PET}")
    fun getAll(): Flow<List<Pet>>

    @Query("SELECT DISTINCT breed FROM ${AppDatabase.TABLE_PET}  WHERE breed is not null;")
    fun getBreeds(): Flow<List<String>>

    @TypeConverters(EnumsConverter::class)
    @Query("SELECT DISTINCT gender as name FROM ${AppDatabase.TABLE_PET} WHERE gender is not null;")
    fun getGenders(): Flow<List<Gender>>

    @TypeConverters(EnumsConverter::class)
    @Query("SELECT DISTINCT size as name FROM ${AppDatabase.TABLE_PET} WHERE size is not null;")
    fun getSizes(): Flow<List<PetSize>>

    @Insert
    fun insertAll(vararg pets: Pet)

    @Delete
    fun delete(pet: Pet)
}
