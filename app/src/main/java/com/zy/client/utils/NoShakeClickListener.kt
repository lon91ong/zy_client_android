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
package com.zy.client.utils

import android.view.View
import kotlin.math.abs

/**
 * 事件防抖
 *
 * 1.既适用于单个`View`事件防抖, 也适用于`Adapter`中`ItemView`事件防抖
 * 2.如果事件为跳转到新的`Activity`, 该`Activity`启动模型应为`android:launchMode="singleTop"`
 */
abstract class NoShakeClickListener @JvmOverloads constructor(interval: Long = 500L) :
    View.OnClickListener {

    private var mTimeInterval = 500L
    private var mLastClickTime: Long = 0   //最近一次点击的时间
    private var mLastClickViewId = 0       //最近一次点击的控件ID

    init {
        mTimeInterval = interval
    }

    override fun onClick(v: View) {
        if (isFastDoubleClick(v, mTimeInterval)) onFastClick(v) else onSingleClick(v)
    }

    /**
     * 是否是快速点击
     *
     * @param v        点击的控件
     * @param interval 时间间期（毫秒）
     * @return true:是，false:不是
     */
    private fun isFastDoubleClick(v: View, interval: Long): Boolean {
        val viewId = v.id
        val nowTime = System.currentTimeMillis()
        val timeInterval = abs(nowTime - mLastClickTime)
        return if (timeInterval < interval && viewId == mLastClickViewId) {
            // 快速点击事件
            true
        } else {
            // 单次点击事件
            mLastClickTime = nowTime
            mLastClickViewId = viewId
            false
        }
    }

    protected open fun onFastClick(v: View?) {}
    protected abstract fun onSingleClick(v: View?)
}