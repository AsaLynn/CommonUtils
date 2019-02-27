package com.zcommon.lib;

import android.os.Environment;

/**
 * Created by zxn on 2019/2/27.
 */
public class SDUtil {

    /**
     * 获取sd卡的根目录.
     *
     * @return sd卡的根目录
     */
    public static String getSDRoot() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }
}
