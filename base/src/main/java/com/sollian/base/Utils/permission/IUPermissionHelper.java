package com.sollian.base.Utils.permission;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.sollian.base.Utils.BaseContext;
import com.sollian.base.Utils.OsVersionUtil;
import com.sollian.base.Utils.Reflector;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author sollian on 2017/6/29. 需要等一次回调结束才可以进行第二次调用
 */
public class IUPermissionHelper implements IPermission {
    private static final int REQUEST_CODE = 0X88;

    private final IPermission iPermission;
    private final List<String> checkPermissions = new ArrayList<>();

    private IPermissionCallback permissionCallback;

    public IUPermissionHelper(IPermission iPermission) {
        this.iPermission = iPermission;
        if (!(iPermission instanceof Activity)
                && !(iPermission instanceof Fragment)) {
            throw new IllegalArgumentException("iPermission must be activity or fragment");
        }
    }

    @Nullable
    public static IPermission getIPermission(Object obj) {
        IPermission iPermission = null;
        if (obj instanceof IPermission) {
            iPermission = (IPermission) obj;
        } else if (obj instanceof ContextWrapper) {
            Context baseContext = ((ContextWrapper) obj).getBaseContext();
            if (baseContext instanceof IPermission) {
                iPermission = (IPermission) baseContext;
            }
        }
        return iPermission;
    }

    public static boolean hasPermissions(String[] permissions) {
        return getDeniedPermission(permissions).isEmpty();
    }

    @Override
    public void checkPermission(
            @NonNull
                    String[] permissions, IPermissionCallback callback) {
        permissionCallback = callback;
        List<String> deniedPermissions = getDeniedPermission(permissions);

        if (deniedPermissions.isEmpty()) {
            permissionCallback.onCheckPermission(deniedPermissions);
            return;
        }

        if (iPermission instanceof Activity) {
            ((Activity) iPermission).requestPermissions(deniedPermissions.toArray(
                    new String[deniedPermissions.size()]), REQUEST_CODE);
        } else if (iPermission instanceof Fragment) {
            ((Fragment) iPermission).requestPermissions(deniedPermissions.toArray(
                    new String[deniedPermissions.size()]), REQUEST_CODE);
        }

        checkPermissions.clear();
        checkPermissions.addAll(deniedPermissions);
    }

    public void onRequestPermissionsResult(int requestCode,
            @NonNull
                    String[] permissions,
            @NonNull
                    int[] grantResults) {
        if (requestCode != REQUEST_CODE) {
            return;
        }

        List<String> failedPermissions = new ArrayList<>(checkPermissions);
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                failedPermissions.remove(permissions[i]);
            }
        }

        permissionCallback.onCheckPermission(failedPermissions);
    }

    private static List<String> getDeniedPermission(String[] permissions) {
        if (!OsVersionUtil.hasM() || permissions.length == 0) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        for (String permission : permissions) {
            if (BaseContext.Companion.getContext().checkSelfPermission(permission) ==
                    PackageManager.PERMISSION_DENIED) {
                result.add(permission);
            }
        }
        return result;
    }

    public static class AppOps {
        public static boolean isNotificationEnabled() {
            return check2("OP_POST_NOTIFICATION");
        }

        public static boolean isSysAlertWindowEnabled() {
            return !OsVersionUtil.hasM() || check1(AppOpsManager.OPSTR_SYSTEM_ALERT_WINDOW);
        }

        public static boolean isLocationEnabled() {
            return !OsVersionUtil.hasM()
                    || check1(AppOpsManager.OPSTR_FINE_LOCATION)
                    && check1(AppOpsManager.OPSTR_COARSE_LOCATION);
        }

        public static boolean isContactEnabled() {
            return !OsVersionUtil.hasM() || check1(AppOpsManager.OPSTR_READ_CONTACTS);
        }

        public static boolean isDeviceEnabled() {
            return !OsVersionUtil.hasM() || check1(AppOpsManager.OPSTR_READ_PHONE_STATE);
        }

        public static boolean isCallEnabled() {
            return !OsVersionUtil.hasM() || check1(AppOpsManager.OPSTR_CALL_PHONE);
        }

        private static boolean check1(String op) {
            if (!OsVersionUtil.hasM()) {
                return true;
            }

            AppOpsManager appOpsManager = getAppOpsManager();
            if (appOpsManager == null) {
                return true;
            }
            ApplicationInfo appInfo = BaseContext.Companion.getContext().getApplicationInfo();
            String pkg = BaseContext.Companion.getContext().getApplicationContext()
                                              .getPackageName();
            int uid = appInfo.uid;

            return appOpsManager.checkOpNoThrow(op, uid, pkg) == AppOpsManager.MODE_ALLOWED;
        }

        private static boolean check2(String op) {
            if (!OsVersionUtil.hasM()) {
                return true;
            }

            AppOpsManager appOpsManager = getAppOpsManager();
            ApplicationInfo appInfo = BaseContext.Companion.getContext().getApplicationInfo();
            String pkg = BaseContext.Companion.getContext().getApplicationContext()
                                              .getPackageName();
            int uid = appInfo.uid;

            try {
                Class<?> appOpsClass = Class.forName(AppOpsManager.class.getName());
                Method checkOpNoThrowMethod = Reflector.getMethodSafe(appOpsClass, "checkOpNoThrow",
                        int.class, int.class, String.class);
                Field opPostNotificationValue = Reflector.getFieldSafe(appOpsClass, op);
                Integer value = opPostNotificationValue == null
                                ? null : (Integer) opPostNotificationValue.get(int.class);
                boolean result;
                if (value == null) {
                    result = false;
                } else {
                    result = checkOpNoThrowMethod != null
                            && (Integer) checkOpNoThrowMethod.invoke(appOpsManager, value, uid, pkg)
                            == AppOpsManager.MODE_ALLOWED;
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    private static AppOpsManager getAppOpsManager() {
        try {
            if (OsVersionUtil.hasKitKat()) {
                return (AppOpsManager) BaseContext.Companion.getContext()
                                                            .getSystemService(
                                                                    Context.APP_OPS_SERVICE);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }
}
