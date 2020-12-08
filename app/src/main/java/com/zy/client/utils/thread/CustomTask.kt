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
package com.zy.client.utils.thread

/**
 * Task，对回调做catch，防止崩溃
 *
 * @author javakam
 */
class CustomTask<T>(private val doInBackground: (() -> (T?))?, private val callback: ((T?) -> Unit)?) :
    ThreadUtils.Task<T>() {
    override fun doInBackground(): T? {
        try {
            return doInBackground?.invoke()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    override fun onSuccess(result: T?) {
        try {
            callback?.invoke(result)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onFail(t: Throwable?) {
        try {
            callback?.invoke(null)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCancel() {
        try {
            callback?.invoke(null)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}