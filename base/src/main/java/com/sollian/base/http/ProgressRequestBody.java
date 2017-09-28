package com.sollian.base.http;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * @author sollian on 2017/9/28.
 */

public class ProgressRequestBody extends RequestBody {
    private final String                url;
    private final RequestBody           requestBody;
    private final IHttpProgressListener listener;

    private BufferedSink bufferedSink;

    public ProgressRequestBody(String url, RequestBody requestBody,
            IHttpProgressListener listener) {
        this.url = url;
        this.requestBody = requestBody;
        this.listener = listener;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    @Override
    public void writeTo(
            @NonNull
                    BufferedSink sink) throws IOException {
        if (bufferedSink == null) {
            bufferedSink = Okio.buffer(getSink(sink));
        }
        requestBody.writeTo(bufferedSink);
        bufferedSink.flush();
    }

    private Sink getSink(Sink sink) {
        return new ForwardingSink(sink) {
            private long bytesWritten;
            private long contentLength;

            @Override
            public void write(
                    @NonNull
                            Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                if (contentLength == 0) {
                    contentLength = contentLength();
                }
                bytesWritten += byteCount;

                if (listener != null) {
                    listener.onUpdate(url, bytesWritten, contentLength);
                }
            }
        };
    }
}
