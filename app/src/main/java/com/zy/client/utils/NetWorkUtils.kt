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
package com.zy.client.utils;

import android.annotation.SuppressLint
import android.content.Context
import android.telephony.TelephonyManager

object NetConstants {
    /**
     * wifi net work
     */
    const val NETWORK_WIFI = "WIFI"

    /**
     * "2G" networks
     */
    const val NETWORK_CLASS_2_G = "2G"

    /**
     * "3G" networks
     */
    const val NETWORK_CLASS_3_G = "3G"

    /**
     * "4G" networks
     */
    const val NETWORK_CLASS_4_G = "4G"

    /**
     * "5G" networks
     */
    const val NETWORK_CLASS_5_G = "5G"
}

object NetWorkUtils {

    @SuppressLint("MissingPermission")
    fun getNetWorkClass(context: Context): String {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        @Suppress("DEPRECATION")
        return when (telephonyManager.networkType) {
            TelephonyManager.NETWORK_TYPE_GPRS, TelephonyManager.NETWORK_TYPE_EDGE, TelephonyManager.NETWORK_TYPE_CDMA, TelephonyManager.NETWORK_TYPE_1xRTT, TelephonyManager.NETWORK_TYPE_IDEN -> NetConstants.NETWORK_CLASS_2_G
            TelephonyManager.NETWORK_TYPE_UMTS, TelephonyManager.NETWORK_TYPE_EVDO_0, TelephonyManager.NETWORK_TYPE_EVDO_A, TelephonyManager.NETWORK_TYPE_HSDPA, TelephonyManager.NETWORK_TYPE_HSUPA, TelephonyManager.NETWORK_TYPE_HSPA, TelephonyManager.NETWORK_TYPE_EVDO_B, TelephonyManager.NETWORK_TYPE_EHRPD, TelephonyManager.NETWORK_TYPE_HSPAP -> NetConstants.NETWORK_CLASS_3_G
            TelephonyManager.NETWORK_TYPE_LTE -> NetConstants.NETWORK_CLASS_4_G
            TelephonyManager.NETWORK_TYPE_NR -> NetConstants.NETWORK_CLASS_5_G
            else                                                                                                                                                                                                                                                                                                                                         -> NetConstants.NETWORK_WIFI
        }
    }

    fun isWifi(context: Context): Boolean {
        return NetConstants.NETWORK_WIFI == getNetWorkClass(context)
    }
}