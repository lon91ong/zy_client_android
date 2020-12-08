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
package com.zy.client.ui.home

import com.zy.client.base.BaseFragment
import com.zy.client.bean.Classify
import com.zy.client.base.BaseTabPagerFragment
import com.zy.client.common.HOME_LIST_TID_NEW
import com.zy.client.common.filterHealthyLife
import com.zy.client.common.isHealthLife
import com.zy.client.views.loader.LoadState

class HomeTabPagerFragment : BaseTabPagerFragment() {

    override fun getItemFragment(classify: Classify): BaseFragment {
        return HomeListFragment.instance(classify.id.toString())
    }

    override fun initData() {
        super.initData()

        mRepo?.getHomeData {
            if (it == null) {
                mStatusView.setLoadState(LoadState.ERROR)
                return@getHomeData
            }

            if (mClassifyList.isNotEmpty()) mClassifyList.clear()
            mClassifyList.add(Classify(HOME_LIST_TID_NEW, "最新"))
            mClassifyList.addAll(it.classifyList.filter { classify ->
                !classify.id.isNullOrBlank() && !classify.name.isNullOrBlank()
                        && (if (isHealthLife()) true else (!filterHealthyLife(classify.name)))
            } as ArrayList<Classify>)

            mViewPagerAdapter.notifyDataSetChanged()
            mStatusView.setLoadState(LoadState.SUCCESS)
        }
    }

}