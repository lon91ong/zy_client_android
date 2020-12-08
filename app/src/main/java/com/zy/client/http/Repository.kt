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
package com.zy.client.http

import com.zy.client.bean.Cctv
import com.zy.client.bean.HomeData
import com.zy.client.bean.VideoDetail
import com.zy.client.bean.VideoEntity

data class CommonRequest(
    val key: String = "",
    val name: String = "",
    val baseUrl: String = "",
    val downloadBaseUrl: String = ""
)

interface IRepository {

    /**
     * 首页
     */
    fun getHomeData(callback: (t: HomeData?) -> Unit)

    /**
     * 分类对应视频列表
     */
    fun getChannelList(page: Int, tid: String, callback: (t: List<VideoEntity>?) -> Unit)

    /**
     * 搜索
     */
    fun search(searchWord: String, page: Int, callback: (t: List<VideoEntity>?) -> Unit)

    /**
     * 视频详情
     */
    fun getVideoDetail(id: String, callback: (t: VideoDetail?) -> Unit)

    /**
     * 电视预告
     */
    fun getCCTVMenu(tvId: String, callback: (t: Cctv?) -> Unit)

}