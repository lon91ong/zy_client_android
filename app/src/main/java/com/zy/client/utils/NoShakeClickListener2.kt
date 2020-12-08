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

import kotlin.math.abs

/**
 * 事件防抖
 * 注: 不仅适用于 View , 其他控件如: MenuItem 同样适用
 *
 * 1.既适用于单个`View`事件防抖, 也适用于`Adapter`中`ItemView`事件防抖
 * 2.如果事件为跳转到新的`Activity`, 该`Activity`启动模型应为`android:launchMode="singleTop"`
 */
open class NoShakeClickListener2 @JvmOverloads constructor(interval: Long = 500L) {

    private var mTimeInterval = 500L
    private var mLastClickTime: Long = 0   //最近一次点击的时间
    private var mLastClick: Any? = null    //最近一次点击的控件 View or MenuItem ...

    init {
        mTimeInterval = interval
    }

    fun proceedClick() {
        if (isFastClick(null, mTimeInterval)) onFastClick(null) else onSingleClick(null)
    }

    fun <T> proceedClick(item: T?) {
        if (isFastClick(item, mTimeInterval)) onFastClick(item) else onSingleClick(item)
    }

    /**
     * 是否是快速点击
     *
     * @param item      点击的控件 View or MenuItem ...
     * @param interval 时间间期（毫秒）
     * @return true:是，false:不是
     */
    private fun <T> isFastClick(item: T?, interval: Long): Boolean {
        val nowTime = System.currentTimeMillis()
        val timeInterval = abs(nowTime - mLastClickTime)
        return if (timeInterval < interval && item == mLastClick) {
            // 快速点击事件
            true
        } else {
            // 单次点击事件
            mLastClickTime = nowTime
            mLastClick = item
            false
        }
    }

    protected open fun onFastClick(item: Any?) {}
    protected open fun onSingleClick(item: Any?) {}
}