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
package com.zy.client.ui.search

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zy.client.R
import com.zy.client.base.BaseLoadMoreAdapter
import com.zy.client.http.ConfigManager
import com.zy.client.utils.ext.noNull
import com.zy.client.http.NetRepository
import com.zy.client.bean.VideoEntity
import com.zy.client.base.BaseListFragment
import com.zy.client.common.AppRouter

/**
 * 搜索结果页
 */
class SearchResultFragment : BaseListFragment<VideoEntity, BaseViewHolder>() {

    private lateinit var source: NetRepository
    private lateinit var searchWord: String

    companion object {
        fun instance(sourceKey: String, searchWord: String): SearchResultFragment {
            return SearchResultFragment().apply {
                arguments = bundleOf("source_key" to sourceKey, "search_word" to searchWord)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        source = ConfigManager.generateSource(arguments?.getString("source_key").noNull())
        searchWord = arguments?.getString("search_word").noNull()
    }

    override fun getListAdapter(): BaseLoadMoreAdapter<VideoEntity, BaseViewHolder> {
        return SearchResultAdapter().apply {
            setOnItemClickListener { _, _, position ->
                AppRouter.toVideoDetailActivity(
                    baseActivity,
                    source.req.key,
                    data[position].id.noNull()
                )
            }
        }
    }

    override fun getListLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    override fun loadData(page: Int, callback: (list: List<VideoEntity>?) -> Unit) {
        if (searchWord.isBlank()) {
            callback.invoke(arrayListOf())
        } else {
            source.search(searchWord, page) {
                callback.invoke(it)
            }
        }
    }

    //搜索结果适配器
    inner class SearchResultAdapter :
        BaseLoadMoreAdapter<VideoEntity, BaseViewHolder>(R.layout.item_search_result) {
        override fun convert(holder: BaseViewHolder, item: VideoEntity) {
            holder.setText(R.id.tvName, item.name.noNull("--"))
            holder.setText(R.id.tvType, item.type.noNull("--"))
        }
    }

}