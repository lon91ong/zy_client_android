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
package com.zy.client.base

import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.zy.client.R
import com.zy.client.bean.Classify
import com.zy.client.http.ConfigManager
import com.zy.client.http.IRepository
import com.zy.client.views.loader.Loader
import com.zy.client.views.loader.LoaderLayout

abstract class BaseTabPagerFragment : BaseFragment() {

    protected lateinit var mStatusView: LoaderLayout
    protected lateinit var mTabLayout: TabLayout
    protected lateinit var mViewPager: ViewPager
    protected lateinit var mViewPagerAdapter: ViewPageAdapter

    //
    protected var mRepo: IRepository? = null
    protected var mClassifyList = ArrayList<Classify>()

    override fun getLayoutId(): Int = R.layout.fragment_tab_pager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRepo = ConfigManager.curUseSourceConfig()
    }

    override fun initView() {
        super.initView()
        mStatusView = rootView.findViewById(R.id.statusView)
        mTabLayout = rootView.findViewById(R.id.tabLayout)
        mViewPager = rootView.findViewById(R.id.viewpager)

        mViewPagerAdapter = ViewPageAdapter()
        mViewPager.adapter = mViewPagerAdapter
        mViewPager.offscreenPageLimit = 100
        mTabLayout.setupWithViewPager(mViewPager)
    }

    override fun initListener() {
        super.initListener()
        mStatusView.setOnReloadListener(object : Loader.OnReloadListener {
            override fun onReload() {
                initData()
            }
        })
    }

    override fun refreshData() {
        super.refreshData()
        if (!isAdded) return
        mViewPagerAdapter.mCurrFragment.refreshData()
    }

    inner class ViewPageAdapter : FragmentPagerAdapter(
        childFragmentManager,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {
        lateinit var mCurrFragment: BaseFragment

        override fun getItem(position: Int): BaseFragment {
            mCurrFragment = getItemFragment(mClassifyList[position])
            return mCurrFragment
        }

        override fun getCount(): Int = mClassifyList.size

        override fun getPageTitle(position: Int): CharSequence? {
            return mClassifyList[position].name
        }
    }

    abstract fun getItemFragment(classify: Classify): BaseFragment

}