package com.sollian.base.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author sollian
 */
public class NetHelper {

    private static NetHelper INSTANCE = new NetHelper(BaseContext.Companion.getContext());

    private final Context context;

    private NetStatus netStatus = NetStatus.NOTHING;

    private final BroadcastReceiver connectionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            netStatus = updateNetworkType(context);
        }
    };

    private NetHelper(Context context) {
        this.context = context;
        updateNetworkType(context);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        this.context.registerReceiver(connectionReceiver, intentFilter);
    }

    public static NetHelper getInstance() {
        return INSTANCE;
    }

    public boolean isNetAvailable() {
        return getNetworkType() != NetStatus.NONE;
    }

    public boolean isWifi() {
        return getNetworkType() == NetStatus.NETTYPE_WIFI;
    }

    private NetStatus getNetworkType() {
        if (netStatus == NetStatus.NOTHING) {
            netStatus = updateNetworkType(context);
        }
        return netStatus;
    }

    private static NetStatus updateNetworkType(Context context) {
        NetStatus netType = NetStatus.NONE;
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        netType = nType == ConnectivityManager.TYPE_WIFI ? NetStatus.NETTYPE_WIFI
                                                         : NetStatus.NETTYPE_OTHER;
        return netType;
    }

    /**
     * 网络链接状态
     */
    public enum NetStatus {
        // 未定义
        NOTHING,
        // 无网络链接
        NONE,
        NETTYPE_WIFI,
        NETTYPE_OTHER
    }
}
