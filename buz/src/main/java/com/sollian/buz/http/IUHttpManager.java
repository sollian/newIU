package com.sollian.buz.http;

import com.sollian.base.http.AbsIUHttpManager;
import com.sollian.base.http.IHttpProgressListener;
import com.sollian.base.http.ProgressInterceptor;

import okhttp3.OkHttpClient;

/**
 * @author sollian on 2017/9/28.
 */

public final class IUHttpManager extends AbsIUHttpManager {
    private String userName;
    private String password;

    private static class Singleton {
        static final IUHttpManager instance = new IUHttpManager();
    }

    private IUHttpManager() {
    }

    public static IUHttpManager getInstance() {
        return Singleton.instance;
    }

    public void initUserInfo(String name, String pwd) {
        userName = name;
        password = pwd;
    }

    public OkHttpClient getDownloadClient(IHttpProgressListener listener) {
        return getOkHttpClientBuilder().addNetworkInterceptor(new ProgressInterceptor(listener))
                                       .build();
    }

    @Override
    protected String getUserName() {
        return userName;
    }

    @Override
    protected String getPassword() {
        return password;
    }
}
