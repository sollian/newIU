package com.sollian.base.http;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.sollian.base.Utils.NetHelper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/**
 * @author sollian on 2017/9/28.
 */

public abstract class AbsIUHttpManager {
    private final Call.Factory client = getOkHttpClientBuilder().build();

    @Nullable
    public Response syncSend(Request request) {
        if (!NetHelper.getInstance().isNetAvailable()) {
            return ErrorResponse.NO_NET;
        }
        try {
            return client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ErrorResponse.UNKNOWN;
    }

    protected final OkHttpClient.Builder getOkHttpClientBuilder() {
        return new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .authenticator(new Authenticator() {
                    @Override
                    public Request authenticate(
                            @NonNull
                                    Route route,
                            @NonNull
                                    Response response) {
                        String credential = Credentials.basic(getUserName(), getPassword());
                        return response.request().newBuilder()
                                       .header("Authorization", credential).build();

                    }
                });
    }

    protected abstract String getUserName();

    protected abstract String getPassword();
}
