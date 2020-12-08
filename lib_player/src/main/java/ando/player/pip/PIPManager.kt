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
package ando.player.pip

import ando.player.IjkVideoView
import ando.player.setting.UserSetting.PIP
import ando.player.utils.VideoUtils
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import com.dueeeke.videoplayer.player.VideoView
import com.dueeeke.videoplayer.player.VideoViewManager

/**
 * 悬浮播放
 */
class PIPManager private constructor() {
    private val mVideoView: IjkVideoView
    private val mFloatView: FloatView
    private val mFloatController: FloatController

    private var mActClass: Class<*>? = null
    var isStartFloatWindow = false
    var playingPosition = -1
    val cacheData: Bundle by lazy { Bundle() }

    var actClass: Class<*>?
        get() = mActClass
        set(cls) {
            mActClass = cls
        }

    fun clearCacheData() {
        cacheData.clear()
    }

    fun startFloatWindow() {
        if (isStartFloatWindow) return
        VideoUtils.removeViewFormParent(mVideoView)
        mVideoView.setVideoController(mFloatController)
        mFloatController.setPlayState(mVideoView.currentPlayState)
        mFloatController.setPlayerState(mVideoView.currentPlayerState)
        mFloatView.addView(mVideoView)
        mFloatView.addToWindow()
        isStartFloatWindow = true
    }

    fun stopFloatWindow() {
        if (!isStartFloatWindow) return
        mFloatView.removeAllViews()
        mFloatView.removeFromWindow()
        VideoUtils.removeViewFormParent(mVideoView)
        isStartFloatWindow = false
    }

    fun resume() {
        if (isStartFloatWindow) return
        mVideoView.resume()
    }

    fun pause() {
        if (isStartFloatWindow) return
        mVideoView.pause()
    }

    fun release() {
        if (isStartFloatWindow) return
        VideoUtils.removeViewFormParent(mVideoView)
        mVideoView.release()
        mVideoView.setVideoController(null)
        playingPosition = -1
        mActClass = null
    }

    fun onBackPressed(): Boolean {
        return !isStartFloatWindow && mVideoView.onBackPressed()
    }

    /**
     * 显示悬浮窗
     */
    fun setFloatViewVisible() {
        if (isStartFloatWindow) {
            mVideoView.resume()
            mFloatView.visibility = View.VISIBLE
        }
    }

    fun getPlayer(): IjkVideoView? = mVideoView

    companion object {
        private lateinit var context: Context
        private var instance: PIPManager? = null

        fun init(ctx: Context): PIPManager? {
            context = ctx.applicationContext
            if (instance == null) {
                synchronized(PIPManager::class.java) {
                    if (instance == null) {
                        instance = PIPManager()
                    }
                }
            }
            return instance
        }

        @JvmStatic
        fun get(): PIPManager? {
            if (instance == null) {
                synchronized(PIPManager::class.java) {
                    if (instance == null) {
                        instance = PIPManager()
                    }
                }
            }
            return instance
        }
    }

    init {
        mVideoView = IjkVideoView(context.applicationContext)
        VideoViewManager.instance().add(mVideoView, PIP)
        mFloatController = FloatController(context)
        mFloatView = FloatView(context, 0, 0)
    }
}