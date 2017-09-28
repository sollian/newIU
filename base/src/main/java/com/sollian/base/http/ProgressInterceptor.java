package com.sollian.base.http;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author sollian on 2017/9/28.
 */

public class ProgressInterceptor implements Interceptor {
    private final IHttpProgressListener listener;

    public ProgressInterceptor(IHttpProgressListener listener) {
        this.listener = listener;
    }

    @Override
    public Response intercept(
            @NonNull
                    Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        return response.newBuilder()
                       .body(new ProgressResponseBody(request.url(), response.body(), listener))
                       .build();
    }
}
