package cn.dota.onkeylayoutdemo.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class APP extends Application {
    private static APP APP;
    public static Context APPcontext;
    public static SharedPreferences SPf; // shared


    @Override
    public void onCreate() {
        super.onCreate();
        APP = this;
        APPcontext = getApplicationContext();
        init();
    }


    private void init() {
        SPf = getSharedPre();
    }
    private SharedPreferences getSharedPre() {
        if (SPf == null) {
            SPf = getSharedPreferences("data", Activity.MODE_PRIVATE);
        }else {
            return SPf;
        }
        return SPf;
    }

    // toast封装
    private static Toast toast;

    public static void mToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(APPcontext, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    // 判断string是否为空
    public static boolean NotNull(String str) {
        if (str == null || str.equals("") || "null".equalsIgnoreCase(str)) {
            return false;
        }
        return true;
    }

    public static APP getInstance() {
        if (APP == null) {
            APP = new APP();
        }
        return APP;
    }
}
