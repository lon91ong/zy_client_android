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

import com.zy.client.utils.thread.CustomTask
import com.zy.client.utils.thread.ThreadUtils
import org.litepal.LitePal
import java.util.*

/**
 * 搜索历史的数据库操作
 *
 * @author javakam
 */
object SearchHistoryDBUtils {

    private fun save(searchWord: String?): Boolean {
        if (searchWord.isNullOrBlank()) return false
        val historyModel =
            LitePal.where("searchWord = ?", searchWord).findFirst(SearchHistoryModel::class.java)

        if (historyModel != null && historyModel.isSaved) return true

        val searchHistoryDBModel = SearchHistoryModel()
        searchHistoryDBModel.searchWord = searchWord
        searchHistoryDBModel.updateData = Date()
        return searchHistoryDBModel.save()
    }

    private fun searchAll(): List<SearchHistoryModel>? {
        val list = LitePal.where("searchWord not null").order("updateData")
            .find(SearchHistoryModel::class.java)
        list?.reverse()
        return list
    }

    fun saveAsync(searchWord: String, callback: ((Boolean) -> Unit)? = null) {
        ThreadUtils.executeByCpu(CustomTask({
            save(searchWord)
        }, {
            callback?.invoke(it ?: false)
        }))
    }

    fun searchAllAsync(callback: ((List<SearchHistoryModel>?) -> Unit)?) {
        ThreadUtils.executeByCpu(CustomTask({
            searchAll()
        }, {
            callback?.invoke(it)
        }))
    }

    fun deleteAll(): Boolean {
        return LitePal.deleteAll(SearchHistoryModel::class.java) > 0
    }
}