package com.sollian.base.Utils;

import android.os.Environment;

/**
 * @author sollian on 2017/9/27.
 */

public class DirUtil {
    private static final String ROOT = "aiyou/";

    private static final String IMAGE = ROOT + "images/";
    private static final String FILE  = ROOT + "files/";

    public static String getImageDir() {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + '/' + IMAGE;
    }

    public static String getFileDir() {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + '/' + FILE;
    }
}
