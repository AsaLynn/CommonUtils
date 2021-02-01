package com.zxn.utils

import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import java.io.File
import java.lang.reflect.Field

/**
 * 存储在sd卡上的SharedPreferences
 * 原文：https://blog.csdn.net/qq_28822993/article/details/53895627
 * Created by zxn on 2019/2/27.
 */
object SdSpUtil {
    /**
     * 存储的文件名
     */
    private const val FILE_NAME = "setting"
    private var mXmlPath = SDUtil.sDRoot

    /**
     * 配置存储在sd卡上的路径,初始化的时候调用,默认是存放在sd的根目录中的.
     *
     * @param sdPath sd中的目录.
     */
    fun configXmlPath(sdPath: String) {
        mXmlPath = SDUtil.sDRoot + File.separator + sdPath
    }

    /**
     * 保存数据到文件
     *
     * @param context context
     * @param key     key
     * @param data    data
     */
    fun saveData(context: Context, key: String?, data: Any) {
        try {
            //利用java反射机制将XML文件自定义存储
            var field: Field
            // 获取ContextWrapper对象中的mBase变量。该变量保存了ContextImpl对象
            field = ContextWrapper::class.java.getDeclaredField("mBase")
            field.isAccessible = true
            // 获取mBase变量
            val obj = field[context]
            // 获取ContextImpl。mPreferencesDir变量，该变量保存了数据文件的保存路径
            field = obj.javaClass.getDeclaredField("mPreferencesDir")
            field.isAccessible = true
            // 创建自定义路径
            val file = File(mXmlPath)
            // 修改mPreferencesDir变量的值
            field[obj] = file
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
            //CommFunc.ToastPromptMsg("XML配置文件保存操作失败");
        }
    }

    /**
     * 从文件中读取数据
     *
     * @param context  Context
     * @param key      键
     * @param defValue 默认值
     * @return Object
     */
    fun getData(context: Context, key: String?, defValue: Any): Any? {
        return try {
            //利用java反射机制将XML文件自定义存储
            var field: Field
            // 获取ContextWrapper对象中的mBase变量。该变量保存了ContextImpl对象
            field = ContextWrapper::class.java.getDeclaredField("mBase")
            field.isAccessible = true
            // 获取mBase变量
            val obj = field[context]
            // 获取ContextImpl。mPreferencesDir变量，该变量保存了数据文件的保存路径
            field = obj.javaClass.getDeclaredField("mPreferencesDir")
            field.isAccessible = true
            // 创建自定义路径
            val file = File(mXmlPath)
            // 修改mPreferencesDir变量的值
            field[obj] = file
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
     * @param context Context
     * @param key     key
     * @param data    data
     */
    fun initialData(context: Context, key: String?, data: Any) {
        try {
            if (getData(context, key, "HasNoInitial").toString() == "HasNoInitial") {
                saveData(context, key, data)
            }
        } catch (e: Exception) {
            Log.e("SdSpUtil", "XML配置文件初始化操作异常" + e.message)
        }
    }
}