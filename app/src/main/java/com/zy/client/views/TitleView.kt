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
package com.zy.client.views

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.zy.client.R

/**
 * Title: TitleView
 */
class TitleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private var mIvLeft: ImageView
    private var mIvRight: ImageView
    private var mTvTitle: TextView
    private var mTvRight: TextView

    init {
        val view = inflate(context, R.layout.layout_title_view, null)
        mIvLeft = view.findViewById(R.id.iv_title_back) as ImageView
        mIvRight = view.findViewById(R.id.iv_title_right) as ImageView
        mTvTitle = view.findViewById<View>(R.id.tv_title) as TextView
        mTvRight = view.findViewById<View>(R.id.tv_title_right) as TextView

        mIvLeft.setOnClickListener {
            val activity = context as Activity?
            activity?.onBackPressed()
        }
        this.addView(view)
    }

    /**
     * 设置中央标题名称
     */
    fun setTitle(text: String?) {
        mTvTitle.visibility = if (text.isNullOrBlank()) GONE else VISIBLE
        mTvTitle.text = text
    }

    /**
     * 获取返回控件
     */
    fun getLeftView(): ImageView {
        mIvLeft.visibility = VISIBLE
        return mIvLeft
    }

    fun getRightText(): TextView {
        mIvRight.visibility = GONE
        mTvRight.visibility = VISIBLE
        return mTvRight
    }

    fun getRightImage(): ImageView {
        mTvRight.visibility = GONE
        mIvRight.visibility = VISIBLE
        return mIvRight
    }

}