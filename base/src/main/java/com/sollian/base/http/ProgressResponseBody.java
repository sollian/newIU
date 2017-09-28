package com.sollian.base.http;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * @author sollian on 2017/9/28.
 */

class ProgressResponseBody extends ResponseBody {
    private final HttpUrl               httpUrl;
    private final ResponseBody          responseBody;
    private final IHttpProgressListener listener;

    private BufferedSource bufferedSource;

    ProgressResponseBody(HttpUrl httpUrl, ResponseBody responseBody,
            IHttpProgressListener listener) {
        this.httpUrl = httpUrl;
        this.responseBody = responseBody;
        this.listener = listener;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(getSource(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source getSource(Source source) {
        return new ForwardingSource(source) {
            private long totalBytesRead;

            @Override
            public long read(
                    @NonNull
                            Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                long fullLength = contentLength();
                if (bytesRead == -1) {
                    totalBytesRead = fullLength;
                } else {
                    totalBytesRead += bytesRead;
                }
                if (listener != null) {
                    listener.onUpdate(httpUrl.toString(), bytesRead, fullLength);
                }
                return bytesRead;
            }
        };
    }
}
