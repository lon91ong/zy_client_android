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
package com.zy.client.common

import android.content.Context
import android.os.Environment
import com.zy.client.App
import com.zy.client.R

/**
 * Title: Global
 * <p>
 * Description:
 * </p>
 * @author javakam
 */

const val SOURCE_KEY = "source_key"
const val ID = "id"
const val TV_BEAN = "tv"

//eg: "${BROWSER_URL}${mVideo?.playUrl?.noNull()}"
const val BROWSER_URL = "http://zyplayer.fun/player/player.html?url="

const val SP_NET_SOURCE_KEY = "ShareConfig"
const val SP_HEALTHY_LIFE = "HealthyLife"

const val HOME_LIST_TID_NEW = "new"
const val HOME_SPAN_COUNT = 3
const val VIDEO_VIEW_HEIGHT = R.dimen.dp_210

//截图保存路径
fun getScreenShotPath(): String =
    "${Environment.DIRECTORY_PICTURES}/${App.instance.getString(R.string.app_name)}"

//健康生活
fun filterHealthyLife(s: String): Boolean {
    return (s.contains("福利")
            || s.contains("伦理")
            || s.contains("倫")
            || s.contains("写真")
            || s.contains("VIP", true)
            || s.contains("街拍"))
}

fun switchHealthLife(open: Boolean) {
    App.instance.getSharedPreferences(SP_HEALTHY_LIFE, Context.MODE_PRIVATE).apply {
        edit().putBoolean("healthy_life", open).apply()
    }
}

fun isHealthLife(): Boolean {
    App.instance.getSharedPreferences(SP_HEALTHY_LIFE, Context.MODE_PRIVATE).apply {
        return getBoolean("healthy_life", false)
    }
}