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

import android.content.Intent
import com.zy.client.base.BaseActivity
import com.zy.client.database.SourceModel
import com.zy.client.ui.HistoryActivity
import com.zy.client.ui.video.VideoDetailActivity
import com.zy.client.ui.search.SearchActivity
import com.zy.client.ui.video.VideoTvActivity

/**
 * Title: 页面路由
 * <p>
 * Description:
 * </p>
 * @author javakam
 */
object AppRouter {

    fun toHistoryActivity(activity: BaseActivity) {
        activity.startActivity(Intent(activity, HistoryActivity::class.java))
    }

    fun toSearchActivity(activity: BaseActivity) {
        activity.startActivity(Intent(activity, SearchActivity::class.java))
    }

    fun toVideoDetailActivity(activity: BaseActivity, sourceKey: String, id: String) {
        activity.startActivity(Intent(activity, VideoDetailActivity::class.java).apply {
            putExtra(SOURCE_KEY, sourceKey)
            putExtra(ID, id)
        })
    }

    fun toTvActivity(activity: BaseActivity,bean:SourceModel) {
        activity.startActivity(Intent(activity, VideoTvActivity::class.java).apply {
            putExtra(TV_BEAN, bean)
        })
    }

}