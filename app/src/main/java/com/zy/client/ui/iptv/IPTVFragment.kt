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

import android.widget.TextView
import com.zy.client.R
import com.zy.client.base.BaseFragment
import com.zy.client.base.BaseTabPagerFragment
import com.zy.client.bean.Classify
import com.zy.client.http.ConfigManager
import com.zy.client.utils.ext.visible
import com.zy.client.views.loader.LoadState

class IPTVFragment : BaseTabPagerFragment() {

    override fun initView() {
        super.initView()
        val tvTitle = rootView.findViewById<TextView>(R.id.tv_title)
        tvTitle.visible()
        tvTitle.text = "电视"
    }

    override fun getItemFragment(classify: Classify): BaseFragment {
        return IPTVListFragment.instance(classify.name.toString())
    }

    override fun initData() {
        super.initData()

        ConfigManager.getIPTVGroups().apply {
            mClassifyList.clear()
            mClassifyList.addAll(this)

            mViewPagerAdapter.notifyDataSetChanged()
            mStatusView.setLoadState(LoadState.SUCCESS)
        }
    }

}