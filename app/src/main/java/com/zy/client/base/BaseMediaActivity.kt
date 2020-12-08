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

import android.view.MenuItem
import com.lxj.xpopup.impl.BottomListPopupView
import com.zy.client.ui.video.VideoController
import com.zy.client.ui.video.WebController
import com.zy.client.utils.status.StatusBarUtils

/**
 * Title: BaseMediaActivity
 * <p>
 * Description: 音视频播放
 * </p>
 * @author javakam
 */
abstract class BaseMediaActivity : BaseActivity() {

    protected var webController: WebController? = null
    protected var videoController: VideoController? = null

    protected var mSelectListDialog: BottomListPopupView? = null

    override fun initStyle(statusBarColor: Int) {
        //super.initStyle(statusBarColor)
        StatusBarUtils.transparentStatusBar(window)
        StatusBarUtils.setLightMode(window)
        StatusBarUtils.setStatusBarView(this, android.R.color.black)
        //or StatusBarUtils.setStatusBarColor(window, Color.BLACK, 0)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        webController?.onResume()
        videoController?.onResume()
    }

    override fun onPause() {
        super.onPause()
        webController?.onPause()
        videoController?.onPause()
    }

    override fun onDestroy() {
        mSelectListDialog?.dismiss()
        webController?.onDestroy()
        videoController?.onDestroy()
        super.onDestroy()
    }

    override fun onBackPressed() {
        if (videoController?.onBackPressed() == true || webController?.onBackPressed() == true) {
            super.onBackPressed()
        }
    }

}