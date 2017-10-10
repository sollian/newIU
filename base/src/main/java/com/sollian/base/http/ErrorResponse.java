package com.sollian.base.http;

import com.sollian.base.R;
import com.sollian.base.Utils.BaseContext;

import okhttp3.Response;

/**
 * @author sollian on 2017/10/10.
 */

public final class ErrorResponse {
    private ErrorResponse() {
    }

    public static final int CODE_NO_NET  = -1;
    public static final int CODE_UNKNOWN = -2;

    public static final Response NO_NET = new Response.Builder()
            .code(CODE_NO_NET)
            .message(BaseContext.Companion.getContext().getString(R.string.no_net))
            .build();

    public static final Response UNKNOWN = new Response.Builder()
            .code(CODE_UNKNOWN)
            .message(BaseContext.Companion.getContext().getString(R.string.error_unknown))
            .build();
}
