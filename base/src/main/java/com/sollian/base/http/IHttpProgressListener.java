package com.sollian.base.http;

/**
 * @author sollian on 2017/9/28.
 */

public interface IHttpProgressListener {
    void onUpdate(String url, long bytesRead, long contentLength);
}
