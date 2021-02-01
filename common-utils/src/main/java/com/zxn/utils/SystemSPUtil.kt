package com.zxn.utils

import android.content.Context
import android.util.Log

/**
 * SharedPreferences的封装工具类.
 * 原文：https://blog.csdn.net/qq_28822993/article/details/53895534
 * Created by zxn on 2019/2/27.
 */
object SystemSPUtil {
    //存储的sharedpreferences文件名
    private const val FILE_NAME = "setting"

    /**
     * 保存数据到文件
     *
     * @param context   context
     * @param key   context
     * @param data  context
     */
    fun saveData(context: Context, key: String?, data: Any) {
        try {
            val type = data.javaClass.simpleName
            val sharedPreferences = context
                    .getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            if ("Integer" == type) {
                editor.putInt(key, (data as Int))
            } else if ("Boolean" == type) {
                editor.putBoolean(key, (data as Boolean))
            } else if ("String" == type) {
                editor.putString(key, data as String)
            } else if ("Float" == type) {
                editor.putFloat(key, (data as Float))
            } else if ("Long" == type) {
                editor.putLong(key, (data as Long))
            }
            editor.commit()
        } catch (e: Exception) {
            Log.e("SdSpUtil", "XML配置文件保存操作异常" + e.message)
        }
    }

    /**
     * 从文件中读取数据
     *
     * @param context   context
     * @param key   context
     * @param defValue  context
     * @return  context
     */
    fun getData(context: Context, key: String?, defValue: Any): Any? {
        return try {
            val type = defValue.javaClass.simpleName
            val sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

            //defValue为为默认值，如果当前获取不到数据就返回它
            if ("Integer" == type) {
                return sharedPreferences.getInt(key, (defValue as Int))
            } else if ("Boolean" == type) {
                return sharedPreferences.getBoolean(key, (defValue as Boolean))
            } else if ("String" == type) {
                return sharedPreferences.getString(key, defValue as String)
            } else if ("Float" == type) {
                return sharedPreferences.getFloat(key, (defValue as Float))
            } else if ("Long" == type) {
                return sharedPreferences.getLong(key, (defValue as Long))
            }
            null
        } catch (e: Exception) {
            defValue
        }
    }

    /**
     * 初始化数据到文件
     * 有就跳过，没有就新增
     *
     * @param context   context
     * @param key   context
     * @param data  context
     */
    fun initialData(context: Context, key: String?, data: Any) {
        try {
            if (getData(context, key, "HasNoInitial").toString() == "HasNoInitial") {
                saveData(context, key, data)
            }
        } catch (e: Exception) {
            Log.e("SdSpUtil", "XML配置文件初始化操作异常" + e.message)
            //CommFunc.ToastPromptMsg("XML配置文件初始化操作失败");
        }
    }
}