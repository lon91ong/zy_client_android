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
package ando.player.utils;

import android.text.TextUtils;

import androidx.collection.LruCache;

import com.dueeeke.videoplayer.player.ProgressManager;

/**
 * @author javakam
 */
public class ProgressManagerImpl extends ProgressManager {

    //保存100条记录
    private static final LruCache<Integer, Long> CACHE = new LruCache<>(100);

    @Override
    public void saveProgress(String url, long progress) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        if (progress == 0) {
            clearSavedProgressByUrl(url);
            return;
        }
        CACHE.put(url.hashCode(), progress);
    }

    @Override
    public long getSavedProgress(String url) {
        if (TextUtils.isEmpty(url)) {
            return 0;
        }
        Long pro = CACHE.get(url.hashCode());
        if (pro == null) {
            return 0;
        }
        return pro;
    }

    public void clearAllSavedProgress() {
        CACHE.evictAll();
    }

    public void clearSavedProgressByUrl(String url) {
        CACHE.remove(url.hashCode());
    }

}