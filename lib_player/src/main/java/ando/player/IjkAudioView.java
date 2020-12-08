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
package ando.player;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dueeeke.videoplayer.player.PlayerFactory;

/**
 * Title: IjkAudioView
 * <p>
 * Description: 自定义播放器 -> 音频
 * </p>
 */
public class IjkAudioView extends BaseIjkVideoView<BaseIjkPlayer> {

    public IjkAudioView(@NonNull Context context) {
        super(context);
    }

    public IjkAudioView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public IjkAudioView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        setPlayerFactory(new PlayerFactory<BaseIjkPlayer>() {
            @Override
            public BaseIjkPlayer createPlayer(Context context) {
                return new BaseIjkPlayer(context);
            }
        });

    }

    @Override
    public void replay(boolean resetPosition) {
        //super.replay(resetPosition);
        if (resetPosition) {
            mCurrentPosition = 0;
        }
        //addDisplay();
        startPrepare(true);
        if (mPlayerContainer != null) {
            mPlayerContainer.setKeepScreenOn(true);
        }
    }


}