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
package ando.player.dialog

import ando.player.setting.Theme
import android.view.ViewGroup

internal object DialogFactory {

    fun createFullSpeedDialog(
            viewGroup: ViewGroup?, isFullScreen: Boolean, initSpeed: Int, theme: Theme?,
            listener: IPlayerCallBack?
    ): SpeedDialog {
        return SpeedDialog.Builder(viewGroup)
                .setInitSpeed(initSpeed)
                .setOnItemClickListener(listener)
                .setIsFullScreen(isFullScreen)
                .setTheme(theme)
                .build()
    }

    fun createVideoListDialog(
            viewGroup: ViewGroup?, isFullScreen: Boolean, data: List<String>, position: Int,
            listener: IPlayerCallBack?
    ): VideoListDialog {
        return VideoListDialog.Builder(viewGroup)
                .setIsFullScreen(isFullScreen)
                .setData(data)
                .setPosition(position)
                .setOnItemClickListener(listener)
                .build();
    }

}