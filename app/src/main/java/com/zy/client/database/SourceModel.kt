/**
 * Copyright 2020 javakam
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zy.client.database

import org.litepal.annotation.Column
import org.litepal.crud.LitePalSupport
import java.io.Serializable

data class SourceModel(
    @Column(unique = true, nullable = false)
    val id: Int = 0,

    val sid: Int = 0,     //source.json
    val tid: Int = 0,     //iptv.json
    val key: String = "",
    val name: String = "",
    val api: String = "",
    val download: String = "",
    val status: String = "",
    val isActive: Boolean = false,
    val url: String = "",
    val group: String = ""
) : LitePalSupport(), Serializable