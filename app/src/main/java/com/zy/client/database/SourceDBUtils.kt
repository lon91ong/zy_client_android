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

import com.zy.client.utils.thread.ThreadUtils
import com.zy.client.utils.thread.CustomTask
import org.litepal.LitePal

object SourceDBUtils {

    fun isIPTVExit(): Boolean = LitePal.isExist(SourceModel::class.java)

    fun saveAllAsync(sourceModels: List<SourceModel>, callback: ((Boolean) -> Unit)? = null) {
        ThreadUtils.executeByCached(CustomTask({
            LitePal.saveAll(sourceModels)
        }, {
            callback?.invoke(it ?: false)
        }))
    }

    fun searchAllSites(): List<SourceModel>? {
        return LitePal.where(" tid == ? ", "-1").find(SourceModel::class.java)
    }

    fun searchAllTv(): List<SourceModel>? {
        return LitePal.where(" sid == ? ", "-1").find(SourceModel::class.java)
    }

    fun searchName(key: String?): String? {
        if (key.isNullOrBlank()) {
            return null
        }
        return LitePal.where(" key = ? ", key).findFirst(SourceModel::class.java)?.name
    }

    fun searchGroupAsync(group: String?, callback: ((List<SourceModel>?) -> Unit)?) {
        if (group.isNullOrBlank()) {
            callback?.invoke(null)
            return
        }
        ThreadUtils.executeByCached(CustomTask<List<SourceModel>?>({
            LitePal.where(" group = ? ", group).find(SourceModel::class.java)
        }, {
            callback?.invoke(it)
        }))
    }

}