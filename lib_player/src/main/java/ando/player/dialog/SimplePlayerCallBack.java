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
package ando.player.dialog;

import org.jetbrains.annotations.NotNull;

public class SimplePlayerCallBack implements IPlayerCallBack {
    @Override
    public void onItemClick() {

    }

    @Override
    public void onSpeedItemClick(int speedType, float speed, String name) {

    }

    @Override
    public void onDefinitionItemClick(int definition, String name, boolean isSmallDefinitionSetChange) {

    }

    @Override
    public void onTimingItemClick(int timing, boolean isSmallTimingSetChange) {

    }

    @Override
    public void showSmallTimingLayout() {

    }

    @Override
    public void showSmallDefinitionLayout() {

    }

    @Override
    public void showSmallSpreedLayout() {

    }

    @Override
    public void showSmallRouteLayout() {

    }

    @Override
    public void onListItemClick(@NotNull String item, int position) {

    }
}