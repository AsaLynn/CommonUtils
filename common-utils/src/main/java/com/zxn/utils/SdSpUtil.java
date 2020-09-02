package com.zxn.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Field;

/**
 * 存储在sd卡上的SharedPreferences
 * 原文：https://blog.csdn.net/qq_28822993/article/details/53895627
 * Created by zxn on 2019/2/27.
 */
public class SdSpUtil {

    /**
     * 存储的文件名
     */
    private static final String FILE_NAME = "setting";

    private static String mXmlPath = SDUtil.getSDRoot();


    /**
     * 配置存储在sd卡上的路径,初始化的时候调用,默认是存放在sd的根目录中的.
     *
     * @param sdPath sd中的目录.
     */
    public static void configXmlPath(String sdPath) {
        mXmlPath = SDUtil.getSDRoot() + File.separator + sdPath;
    }

    /**
     * 保存数据到文件
     *
     * @param context context
     * @param key     key
     * @param data    data
     */
    public static void saveData(Context context, String key, Object data) {
        try {
            //利用java反射机制将XML文件自定义存储
            Field field;
            // 获取ContextWrapper对象中的mBase变量。该变量保存了ContextImpl对象
            field = ContextWrapper.class.getDeclaredField("mBase");
            field.setAccessible(true);
            // 获取mBase变量
            Object obj = field.get(context);
            // 获取ContextImpl。mPreferencesDir变量，该变量保存了数据文件的保存路径
            field = obj.getClass().getDeclaredField("mPreferencesDir");
            field.setAccessible(true);
            // 创建自定义路径
            File file = new File(mXmlPath);
            // 修改mPreferencesDir变量的值
            field.set(obj, file);

            String type = data.getClass().getSimpleName();
            SharedPreferences sharedPreferences = context
                    .getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            if ("Integer".equals(type)) {
                editor.putInt(key, (Integer) data);
            } else if ("Boolean".equals(type)) {
                editor.putBoolean(key, (Boolean) data);
            } else if ("String".equals(type)) {
                editor.putString(key, (String) data);
            } else if ("Float".equals(type)) {
                editor.putFloat(key, (Float) data);
            } else if ("Long".equals(type)) {
                editor.putLong(key, (Long) data);
            }

            editor.commit();
        } catch (Exception e) {
            Log.e("SdSpUtil", "XML配置文件保存操作异常" + e.getMessage());
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
    public static Object getData(Context context, String key, Object defValue) {
        try {
            //利用java反射机制将XML文件自定义存储
            Field field;
            // 获取ContextWrapper对象中的mBase变量。该变量保存了ContextImpl对象
            field = ContextWrapper.class.getDeclaredField("mBase");
            field.setAccessible(true);
            // 获取mBase变量
            Object obj = field.get(context);
            // 获取ContextImpl。mPreferencesDir变量，该变量保存了数据文件的保存路径
            field = obj.getClass().getDeclaredField("mPreferencesDir");
            field.setAccessible(true);
            // 创建自定义路径
            File file = new File(mXmlPath);
            // 修改mPreferencesDir变量的值
            field.set(obj, file);

            String type = defValue.getClass().getSimpleName();
            SharedPreferences sharedPreferences = context.getSharedPreferences
                    (FILE_NAME, Context.MODE_PRIVATE);

            //defValue为为默认值，如果当前获取不到数据就返回它
            if ("Integer".equals(type)) {
                return sharedPreferences.getInt(key, (Integer) defValue);
            } else if ("Boolean".equals(type)) {
                return sharedPreferences.getBoolean(key, (Boolean) defValue);
            } else if ("String".equals(type)) {
                return sharedPreferences.getString(key, (String) defValue);
            } else if ("Float".equals(type)) {
                return sharedPreferences.getFloat(key, (Float) defValue);
            } else if ("Long".equals(type)) {
                return sharedPreferences.getLong(key, (Long) defValue);
            }

            return null;
        } catch (Exception e) {
            return defValue;
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
    public static void initialData(Context context, String key, Object data) {
        try {
            if (getData(context, key, "HasNoInitial").toString().equals("HasNoInitial")) {
                saveData(context, key, data);
            }
        } catch (Exception e) {
            Log.e("SdSpUtil", "XML配置文件初始化操作异常" + e.getMessage());
        }
    }

}
