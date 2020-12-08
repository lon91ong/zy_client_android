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
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.zy.client.R
import com.zy.client.utils.Utils
import com.zy.client.utils.status.StatusBarUtils

abstract class BaseActivity : AppCompatActivity() {

    //系统 DecorView 的根View
    protected lateinit var mView: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mView = findViewById(android.R.id.content)
        initStyle()
        initView(savedInstanceState)
        initListener()
        initData()
    }

    open fun initStyle(statusBarColor: Int = android.R.color.white) {
        StatusBarUtils.transparentStatusBar(window)
        StatusBarUtils.setDarkMode(window)
        StatusBarUtils.setStatusBarView(this, android.R.color.white)
    }

    open fun initView(savedInstanceState: Bundle?) {
    }

    open fun initListener() {
    }

    open fun initData() {
    }

    abstract fun getLayoutId(): Int

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            it?.let {
                if ((it as? IBackPressed)?.onBackPressed() == true) {
                    return
                }
            }
        }
        super.onBackPressed()
    }
}