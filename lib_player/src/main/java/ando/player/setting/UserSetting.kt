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
package ando.player.setting

import android.content.Context
import android.content.SharedPreferences

/**
 * Title: UserSetting
 *
 * Description: 保存用户设置
 */
data class FloatPosition(var mDownX: Int = 0, var mDownY: Int = 0)

object UserSetting {

    const val LIST = "list"         //列表播放
    const val SEAMLESS = "seamless" //无缝播放
    const val PIP = "pip"           //画中画

    private const val PLAYER_SETTING = "player_setting"
    private const val BACKGROUND_PLAY = "background_play"
    private const val FLOAT_X = "float_x"
    private const val FLOAT_Y = "float_y"

    /**
     * 是否允许小窗播放
     * 默认不允许: false
     */
    fun setBackgroundPlay(context: Context, bgPlay: Boolean = false) {
        getSP(context).edit().putBoolean(BACKGROUND_PLAY, bgPlay).apply()
    }

    fun getBackgroundPlay(context: Context): Boolean {
        return getSP(context).getBoolean(BACKGROUND_PLAY, false)
    }

    /**
     * 记录小窗位置
     */
    fun setFloatPosition(context: Context, x: Int = 20, y: Int = 20) {
        getSP(context).edit().putInt(FLOAT_X, x).putInt(FLOAT_Y, y)
            .apply()
    }

    fun getFloatPosition(context: Context): FloatPosition {
        return FloatPosition(
            getSP(context).getInt(FLOAT_X, 20),
            getSP(context).getInt(FLOAT_Y, 20)
        )
    }

    private fun getSP(context: Context): SharedPreferences {
        return context.getSharedPreferences(PLAYER_SETTING, Context.MODE_PRIVATE)
    }

}