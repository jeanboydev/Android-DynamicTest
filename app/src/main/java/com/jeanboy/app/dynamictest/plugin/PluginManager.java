package com.jeanboy.app.dynamictest.plugin;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class PluginManager {

    private static volatile PluginManager instance;
    private static Context mContext;
    private static File optFile;

    private PluginManager(Context context) {
        mContext = context;
        optFile = mContext.getDir("opt", mContext.MODE_PRIVATE);
    }

    public static PluginManager getInstance(Context context) {
        if (instance == null) {
            synchronized (PluginManager.class) {
                if (instance == null) {
                    instance = new PluginManager(context);
                }
            }
        }
        return instance;
    }

    /**
     * 为插件 APK 创建对应的 ClassLoader
     *
     * @param apkPath
     * @return
     */
    private static DexClassLoader createPluginDexClassLoader(String apkPath) {
        DexClassLoader classLoader = new DexClassLoader(apkPath, optFile.getAbsolutePath(), null, null);
        return classLoader;
    }

    /**
     * 为插件 APK 创建对应的 AssetManager
     *
     * @param apkPath
     * @return
     */
    private static AssetManager createPluginAssetManager(String apkPath) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method method = assetManager.getClass().getMethod("addAssertPath", String.class);
            method.invoke(assetManager, apkPath);
            return assetManager;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 为插件 APK 创建对应的 Resources
     *
     * @param apkPath
     * @return
     */
    private static Resources createPluginResources(String apkPath) {
        AssetManager assetManager = createPluginAssetManager(apkPath);
        Resources superResources = mContext.getResources();
        Resources pluginResources = new Resources(assetManager, superResources.getDisplayMetrics(), superResources.getConfiguration());
        return pluginResources;
    }

    public static PluginInfo loadApk(String apkPath) {
        PluginInfo pluginInfo = new PluginInfo();
        pluginInfo.classLoader = createPluginDexClassLoader(apkPath);
        pluginInfo.assetManager = createPluginAssetManager(apkPath);
        pluginInfo.resources = createPluginResources(apkPath);
        return pluginInfo;
    }
}
