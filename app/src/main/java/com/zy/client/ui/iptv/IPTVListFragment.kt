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
package com.zy.client.ui.iptv

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zy.client.R
import com.zy.client.base.BaseListFragment
import com.zy.client.common.AppRouter
import com.zy.client.base.BaseLoadMoreAdapter
import com.zy.client.database.SourceDBUtils
import com.zy.client.database.SourceModel
import com.zy.client.utils.ext.noNull

class IPTVListFragment : BaseListFragment<SourceModel, BaseViewHolder>() {

    //分类
    private lateinit var group: String

    companion object {
        fun instance(group: String): IPTVListFragment {
            return IPTVListFragment().apply {
                arguments = bundleOf("group" to group)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        group = arguments?.getString("group").noNull()
    }

    override fun getListAdapter(): BaseLoadMoreAdapter<SourceModel, BaseViewHolder> {
        return IPTVListAdapter().apply {
            setOnItemClickListener { _, _, position ->
                AppRouter.toTvActivity(baseActivity, data[position])
            }
        }
    }

    override fun getListLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(requireActivity())
    }

    override fun loadData(page: Int, callback: (list: List<SourceModel>?) -> Unit) {
        if (page == 1) {
            SourceDBUtils.searchGroupAsync(group) {
                callback.invoke(it)
            }
        } else {
            callback.invoke(arrayListOf())
        }
    }

    inner class IPTVListAdapter :
        BaseLoadMoreAdapter<SourceModel, BaseViewHolder>(R.layout.item_iptv) {

        override fun convert(holder: BaseViewHolder, item: SourceModel) {
            holder.setText(R.id.tvName, item.name)
            holder.setText(R.id.tvSourceName, item.group)
        }
    }
}