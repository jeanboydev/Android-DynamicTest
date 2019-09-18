package com.jeanboy.app.dynamictest.plugin;

import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

public class PluginInfo {

    public DexClassLoader classLoader;
    public AssetManager assetManager;
    public Resources resources;
}
