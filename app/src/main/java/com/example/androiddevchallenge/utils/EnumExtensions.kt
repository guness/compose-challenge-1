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
package com.example.androiddevchallenge.utils

import android.util.Log
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified E : Enum<E>> E.asString(): String {
    return Json.encodeToString(this).removeSurrounding("\"")
}

inline fun <reified E : Enum<E>> String.asEnum(): E? {
    try {
        return Json.decodeFromString("\"$this\"")
    } catch (e: Exception) {
        Log.e("EnumExt", "cannot find enum for $this", e)
    }
    return null
}
