package com.xuxuexia.youshixiu.easytest;


import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.Reader;
        import java.lang.reflect.Method;
        import java.util.ArrayList;
        import java.util.Iterator;
        import java.util.List;
        import java.util.Map.Entry;
        import java.util.Properties;

import android.app.ActionBar;
import android.app.Activity;
        import android.app.ActivityManager;
        import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.ComponentName;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.pm.PackageInfo;
        import android.content.pm.PackageManager;
        import android.net.Uri;
        import android.net.wifi.WifiConfiguration.Status;
        import android.net.wifi.WifiInfo;
        import android.net.wifi.WifiManager;
        import android.os.Bundle;
        import android.os.Environment;
        import android.provider.Settings;
        import android.text.SpannableString;
        import android.text.style.UnderlineSpan;
        import android.util.DisplayMetrics;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.view.Window;
        import android.view.WindowManager;
        import android.widget.Button;
        import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
        import android.widget.Toast;

public class MainActivity extends TabActivity {
    private String fileDirPath = Environment.getExternalStorageDirectory()
            .getPath()// 得到外部存储卡的数据库的路径名
            + "";// 我要存储的目录
    private String fileName = "test.properties";// 要存储的文件名
    private String damiFileName = "damitv.properties";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost();
        tabHost.addTab(tabHost.newTabSpec("游视秀").setContent(new Intent(this,YoushixiuActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("大米").setContent(new Intent(this,DamiActivity.class)));
        tabHost.setCurrentTab(0);

        // 获取手机分辨率
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        int W = mDisplayMetrics.widthPixels;
        int H = mDisplayMetrics.heightPixels;
        Log.i("Main", "Width = " + W);
        Log.i("Main", "Height = " + H);

        TextView resolution = (TextView) findViewById(R.id.resolution);
        resolution.setText("分辨率： " + H + " * " + W);

        // 获取手机品牌
        String phoneXinghao = android.os.Build.MODEL;
        TextView brand = (TextView) findViewById(R.id.brand);
        brand.setText("品牌型号： " + " " + phoneXinghao);

        // 获取手机系统
        String sv = android.os.Build.VERSION.RELEASE;
        System.out.println(sv);
        TextView systemversion = (TextView) findViewById(R.id.systemversion);
        systemversion.setText("操作系统： " + sv);

        // 获取手机ip ddddddddddd

        // 获取wifi服务
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        // 判断wifi是否开启
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        String ip = intToIp(ipAddress);
        System.out.println(ip);

        TextView et = (TextView) findViewById(R.id.phoneip);
        et.setText("ip： " + ip);


        // 获取手机当前连接的wifi的名字
        int wifiState = wifiManager.getWifiState();
        WifiInfo info = wifiManager.getConnectionInfo();
        String wifiId = info != null ? info.getSSID() : null;
        System.out.println(info);
        System.out.println(wifiId);
        TextView wi = (TextView) findViewById(R.id.phoneconnectwifi);
        wi.setText("当前所连wifi为： " + wifiId);


        // 获取当前wifi的代理信息
        String proxyhost = System.getProperty("http.proxyHost");
        String port = System.getProperty("http.proxyPort");
        if (proxyhost == null || port == null) {
            TextView wp = (TextView) findViewById(R.id.phoneconnectproxy);
            wp.setText("wifi代理 ： 无");
        } else {
            TextView wp = (TextView) findViewById(R.id.phoneconnectproxy);
            wp.setText("wifi代理为： " + proxyhost + " : " + port);
        }

        // 点击刷新按钮，使界面显示最新信息
        Button refreshBtn = (Button) findViewById(R.id.refreshBtn);
        refreshBtn.setOnClickListener(new refreshBtnListener());
    }


    // 刷新app界面dddddddddddd
    class refreshBtnListener implements OnClickListener {
        public void onClick(View v) {
            // 获取当前wifi的代理信息
            String proxyhost = System.getProperty("http.proxyHost");
            String port = System.getProperty("http.proxyPort");
            if (proxyhost == null || port == null) {
                TextView wp = (TextView) findViewById(R.id.phoneconnectproxy);
                wp.setText("wifi代理 ： 无");
            } else {
                TextView wp = (TextView) findViewById(R.id.phoneconnectproxy);
                wp.setText("wifi代理为： " + proxyhost + " : " + port);
            }

            // 获取wifi服务
            WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
            // 判断wifi是否开启
            if (!wifiManager.isWifiEnabled()) {
                wifiManager.setWifiEnabled(true);
            }
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int ipAddress = wifiInfo.getIpAddress();
            String ip = intToIp(ipAddress);
            System.out.println(ip);
            TextView et = (TextView) findViewById(R.id.phoneip);
            et.setText("ip为： " + ip);


            // 获取手机当前连接的wifi的名字
            int wifiState = wifiManager.getWifiState();
            WifiInfo info = wifiManager.getConnectionInfo();
            String wifiId = info != null ? info.getSSID() : null;
            System.out.println(info);
            System.out.println(wifiId);
            TextView wi = (TextView) findViewById(R.id.phoneconnectwifi);
            wi.setText("当前所连wifi为： " + wifiId);

        }
    }

    // ip地址格式转换
    private String intToIp(int i) {

        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF)
                + "." + (i >> 24 & 0xFF);
    }


}
