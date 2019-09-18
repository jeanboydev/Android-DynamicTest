package com.jeanboy.app.dynamictest.patch;

import android.content.Context;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;
import com.jeanboy.app.dynamictest.utils.AppUtil;

import java.io.IOException;

public class AndFixPatchManager {

    private static volatile AndFixPatchManager instance = null;

    private PatchManager patchManager;

    public static AndFixPatchManager getInstance() {
        if (instance == null) {
            synchronized (AndFixPatchManager.class) {
                if (instance == null) {
                    instance = new AndFixPatchManager();
                }
            }
        }
        return instance;
    }


    public void initPatch(Context context) {
        patchManager = new PatchManager(context);
        patchManager.init(AppUtil.getVersionName(context));
        patchManager.loadPatch();
    }

    public void addPatch(String path) {
        if (patchManager == null) return;
        try {
            patchManager.addPatch(path);
            Log.e("===addPatch==","======成功===");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("===addPatch==",e.getMessage());
        }
    }
}
