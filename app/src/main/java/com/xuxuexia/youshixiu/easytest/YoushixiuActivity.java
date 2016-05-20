package com.xuxuexia.youshixiu.easytest;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class YoushixiuActivity extends AppCompatActivity {

    private String fileDirPath = Environment.getExternalStorageDirectory()
            .getPath()// 得到外部存储卡的数据库的路径名
            + "";// 我要存储的目录
    private String fileName = "test.properties";// 要存储的文件名

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_youshixiu);



        // 删除youshixiu文件夹
        Button delbtnButton = (Button) findViewById(R.id.deleteYoushixiu);
        delbtnButton.setOnClickListener(new DeleteyoushixiuListener());

        // 切换到游视秀app的测试环境
        Button switchtoYsxApptestButton = (Button) findViewById(R.id.switchtoYsxTest);
        switchtoYsxApptestButton
                .setOnClickListener(new switchtoYsxApptestListener());

        // 切换到游视秀现网预发布环境
        Button switchtoPreYsxButton = (Button) findViewById(R.id.switchtoPreYsx);
        switchtoPreYsxButton
                .setOnClickListener(new switchtoPreYsxListener());//aaaaa

        // 切换到玩吧社区的测试环境
        Button switchtoWbApptestButton = (Button) findViewById(R.id.switchtoWbTest);
        switchtoWbApptestButton
                .setOnClickListener(new switchtoWbApptestListener());

        // 切换到视频站的测试环境
        Button switchtoSpztestButton = (Button) findViewById(R.id.switchtoSpzTest);
        switchtoSpztestButton
                .setOnClickListener(new switchtoSpztestListener());

        // 切换到开发环境
        Button switchtodevelopButton = (Button) findViewById(R.id.switchtodevelop);
        switchtodevelopButton.setOnClickListener(new switchtodevelopListener());

        // 切换到正式环境
        Button switchtonormalButton = (Button) findViewById(R.id.switchtonormal);
        switchtonormalButton.setOnClickListener(new switchtonormalListener());

        // 清除youshixiu数据
        Button clearyoushixiudata = (Button) findViewById(R.id.cleardata);
        clearyoushixiudata
                .setOnClickListener(new DeleteyoushixiuDataListener());

        // 跳转到WLAN设置界面
        Button enterWLAN = (Button) findViewById(R.id.enterWLAN);
        enterWLAN.setOnClickListener(new EnterWLANListener());

        // 打开游视秀
        Button enteryoushixiu = (Button) findViewById(R.id.enteryoushixiu);
        enteryoushixiu.setOnClickListener(new EnteryoushixiuListener());

        // 进入192.168.1.135
        TextView enter214 = (TextView) findViewById(R.id.linkgong);
        SpannableString content = new SpannableString("192.168.1.135");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        enter214.setText(content);
        enter214.setOnClickListener(new EnterlinkgongListener());
    }

    private void createFileYsxTest() {
        String filePath = fileDirPath + "/" + fileName;// 文件路径
        try {
            File dir = new File(fileDirPath);// 目录路径
            if (!dir.exists()) {// 如果不存在，则创建路径名
                System.out.println("要存储的目录不存在");
                if (dir.mkdirs()) {// 创建该路径名，返回true则表示创建成功
                    System.out.println("已经创建文件存储目录");
                } else {
                    System.out.println("创建目录失败");
                }
            }
            // 目录存在，则将apk中raw中的需要的文档复制到该目录下
            File file = new File(filePath);
            if (!file.exists()) {// 文件不存在
                System.out.println("要打开的文件不存在");
                InputStream ins = getResources().openRawResource(R.raw.test);// 通过raw得到数据资源
                System.out.println("开始读入");
                FileOutputStream fos = new FileOutputStream(file);
                System.out.println("开始写出");
                byte[] buffer = new byte[8192];
                int count = 0;// 循环写出
                while ((count = ins.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();// 关闭流
                ins.close();
                Toast.makeText(getApplicationContext(), "已切换为游视秀测试环境了",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createFilePreYsx() {
        String filePath = fileDirPath + "/" + fileName;// 文件路径
        try {
            File dir = new File(fileDirPath);// 目录路径
            if (!dir.exists()) {// 如果不存在，则创建路径名
                System.out.println("要存储的目录不存在");
                if (dir.mkdirs()) {// 创建该路径名，返回true则表示创建成功
                    System.out.println("已经创建文件存储目录");
                } else {
                    System.out.println("创建目录失败");
                }
            }
            // 目录存在，则将apk中raw中的需要的文档复制到该目录下
            File file = new File(filePath);
            if (!file.exists()) {// 文件不存在
                System.out.println("要打开的文件不存在");
                InputStream ins = getResources().openRawResource(R.raw.testpre);// 通过raw得到数据资源
                System.out.println("开始读入");
                FileOutputStream fos = new FileOutputStream(file);
                System.out.println("开始写出");
                byte[] buffer = new byte[8192];
                int count = 0;// 循环写出
                while ((count = ins.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();// 关闭流
                ins.close();
                Toast.makeText(getApplicationContext(), "已切换为游视秀现网预发布环境了",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void createFileWbTest() {
        String filePath = fileDirPath + "/" + fileName;// 文件路径
        try {
            File dir = new File(fileDirPath);// 目录路径
            if (!dir.exists()) {// 如果不存在，则创建路径名
                System.out.println("要存储的目录不存在");
                if (dir.mkdirs()) {// 创建该路径名，返回true则表示创建成功
                    System.out.println("已经创建文件存储目录");
                } else {
                    System.out.println("创建目录失败");
                }
            }
            // 目录存在，则将apk中raw中的需要的文档复制到该目录下
            File file = new File(filePath);
            if (!file.exists()) {// 文件不存在
                System.out.println("要打开的文件不存在");
                InputStream ins = getResources().openRawResource(R.raw.testwanba);// 通过raw得到数据资源
                System.out.println("开始读入");
                FileOutputStream fos = new FileOutputStream(file);
                System.out.println("开始写出");
                byte[] buffer = new byte[8192];
                int count = 0;// 循环写出
                while ((count = ins.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();// 关闭流
                ins.close();
                Toast.makeText(getApplicationContext(), "已切换为玩吧测试环境了",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void createFileSpzTest() {
        String filePath = fileDirPath + "/" + fileName;// 文件路径
        try {
            File dir = new File(fileDirPath);// 目录路径
            if (!dir.exists()) {// 如果不存在，则创建路径名
                System.out.println("要存储的目录不存在");
                if (dir.mkdirs()) {// 创建该路径名，返回true则表示创建成功
                    System.out.println("已经创建文件存储目录");
                } else {
                    System.out.println("创建目录失败");
                }
            }
            // 目录存在，则将apk中raw中的需要的文档复制到该目录下
            File file = new File(filePath);
            if (!file.exists()) {// 文件不存在
                System.out.println("要打开的文件不存在");
                InputStream ins = getResources().openRawResource(R.raw.testshipinzhan);// 通过raw得到数据资源
                System.out.println("开始读入");
                FileOutputStream fos = new FileOutputStream(file);
                System.out.println("开始写出");
                byte[] buffer = new byte[8192];
                int count = 0;// 循环写出
                while ((count = ins.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();// 关闭流
                ins.close();
                Toast.makeText(getApplicationContext(), "已切换为视频站的测试环境了",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void createFiledevelop() {
        String filePath = fileDirPath + "/" + fileName;// 文件路径
        try {
            File dir = new File(fileDirPath);// 目录路径
            if (!dir.exists()) {// 如果不存在，则创建路径名
                System.out.println("要存储的目录不存在");
                if (dir.mkdirs()) {// 创建该路径名，返回true则表示创建成功
                    System.out.println("已经创建文件存储目录");
                } else {
                    System.out.println("创建目录失败");
                }
            }
            // 目录存在，则将apk中raw中的需要的文档复制到该目录下
            File file = new File(filePath);
            if (!file.exists()) {// 文件不存在
                System.out.println("要打开的文件不存在");
                InputStream ins = getResources().openRawResource(
                        R.raw.testdevelop);// 通过raw得到数据资源
                System.out.println("开始读入");
                FileOutputStream fos = new FileOutputStream(file);
                System.out.println("开始写出");
                byte[] buffer = new byte[8192];
                int count = 0;// 循环写出
                while ((count = ins.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();// 关闭流
                ins.close();
                Toast.makeText(getApplicationContext(), "已切换为游视秀开发环境了",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    // 进入WLAN设置界面
    class EnterWLANListener implements View.OnClickListener {
        public void onClick(View v) {
            Intent wifiSettingsIntent = new Intent(
                    "android.settings.WIFI_SETTINGS");
            startActivity(wifiSettingsIntent);
            // MainActivity.this.finish();
        }
    }

    // 进入youshixiu界面
    class EnteryoushixiuListener implements View.OnClickListener {
        public void onClick(View v) {
            if (isAvilible(YoushixiuActivity.this, "com.youshixiu.gameshow")) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                ComponentName cn = new ComponentName("com.youshixiu.gameshow",
                        "com.youshixiu.gameshow.ui.WelcomeActivity");
                intent.setComponent(cn);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "木有安装游视秀哦",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    // 进入192.168.1.135 gong
    class EnterlinkgongListener implements View.OnClickListener {
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setData(Uri.parse("http://192.168.1.135"));
            intent.setAction(Intent.ACTION_VIEW);
            startActivity(intent);
        }
    }




    // 删除youshixiu文件夹，点击删除按钮的监听器
    class DeleteyoushixiuListener implements View.OnClickListener {

        public void onClick(View v) {
            // DeleteFile(file);
            checkMP4();
        }
    }

    // 切换到游视秀app的测试环境
    class switchtoYsxApptestListener implements View.OnClickListener {
        public void onClick(View v) {

            // 不管有没有test文件，先删除掉，再说，防止开发人员放了开发环境的test文件
            deletetestfile();

            // 然后再放一份test文件 得到sdcard的路径 /storage/emulated/0
            // String lujingString = android.os.Environment
            // .getExternalStorageDirectory().getAbsolutePath();
            createFileYsxTest();///bbbbb
        }
    }

    // 切换到现网预发布环境aaaaa
    class switchtoPreYsxListener implements View.OnClickListener {
        public void onClick(View v) {

            // 不管有没有test文件，先删除掉，再说，防止开发人员放了开发环境的test文件
            deletetestfile();

            // 然后再放一份test文件 得到sdcard的路径 /storage/emulated/0
            // String lujingString = android.os.Environment
            // .getExternalStorageDirectory().getAbsolutePath();
            createFilePreYsx();
        }
    }


    // 切换到玩吧的测试环境
    class switchtoWbApptestListener implements View.OnClickListener {
        public void onClick(View v) {

            // 不管有没有test文件，先删除掉，再说，防止开发人员放了开发环境的test文件
            deletetestfile();

            // 然后再放一份test文件 得到sdcard的路径 /storage/emulated/0
            // String lujingString = android.os.Environment
            // .getExternalStorageDirectory().getAbsolutePath();

            createFileWbTest();
        }
    }

    // 切换到视频站测试环境
    class switchtoSpztestListener implements View.OnClickListener {
        public void onClick(View v) {

            // 不管有没有test文件，先删除掉，再说，防止开发人员放了开发环境的test文件
            deletetestfile();

            // 然后再放一份test文件 得到sdcard的路径 /storage/emulated/0
            // String lujingString = android.os.Environment
            // .getExternalStorageDirectory().getAbsolutePath();

            createFileSpzTest();
        }
    }

    // 切换到开发环境
    class switchtodevelopListener implements View.OnClickListener {
        public void onClick(View v) {

            // 不管有没有test文件，先删除掉，再说，防止开发人员放了开发环境的test文件
            deletetestfile();

            // 然后再放一份test文件 得到sdcard的路径 /storage/emulated/0
            // String lujingString = android.os.Environment
            // .getExternalStorageDirectory().getAbsolutePath();

            createFiledevelop();
        }
    }

    // 切换到正式环境
    class switchtonormalListener implements View.OnClickListener {
        // 删除sdcard下的配置文件
        public void onClick(View v) {
            // 不管有没有test文件，先删除掉，再说，防止开发人员放了开发环境的test文件
            deletetestfile();

            Toast.makeText(getApplicationContext(), "已切换为正式环境了",
                    Toast.LENGTH_SHORT).show();

        }
    }

    // 清除youshixiu缓存数据，点击清除按钮的监听器
    class DeleteyoushixiuDataListener implements View.OnClickListener {
        public void onClick(View v) {
            if (isAvilible(YoushixiuActivity.this, "com.youshixiu.gameshow")) {
                Uri packageURI = Uri.parse("package:"
                        + "com.youshixiu.gameshow");
                Intent intent = new Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        packageURI);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "木有安装游视秀哦",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }


    /**
     * * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * * @param directory
     * */
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }

    /**
     * 递归删除文件和文件夹
     *
     * @param file
     *            要删除的根目录
     */
    public void DeleteFile(File file) {
        if (file.exists() == false) {
            Toast.makeText(getApplicationContext(), "youshixiu文件夹不存在",
                    Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (file.isFile()) {
                file.delete();
                return;
            }
            if (file.isDirectory()) {
                File[] childFile = file.listFiles();
                if (childFile == null || childFile.length == 0) {
                    file.delete();
                    return;
                }
                for (File f : childFile) {
                    DeleteFile(f);
                }
                file.delete();
            }

        }

    }

    /**
     * 递归删除文件和文件夹
     *
     * @param //file
     *            要删除的根目录
     */
    public void checkMP4() {
        String youshixiupath = "/sdcard/youshixiu";// 文件夹的路径
        final File file = new File(youshixiupath);

        String youshixiupath1 = "/sdcard/youshixiu/com.youshixiu.gameshow";// 文件夹的路径
        File file1 = new File(youshixiupath1);

        File[] fl = file1.listFiles();
        if (file1.listFiles() == null) {
            Toast.makeText(getApplicationContext(), "没有文件呢", Toast.LENGTH_SHORT)
                    .show();
        } else {
            System.out.println("f1[0]");
            int count = 0;
            int flag = 0;
            int i;
            System.out.println(fl.length);

            for (i = 0; i < fl.length; i++) {
                if (fl[i].toString().endsWith(".mp4")
                        || fl[i].toString().endsWith(".MP4")) {
                    count++;
                    flag = i;
                }
            }
            System.out.println("countMP4" + count);
//
            if (count != 0) {
                new AlertDialog.Builder(this)
                        .setTitle("有视频文件也要删除吗？")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        // 点击“确认”后的操作：删除youshixiu文件夹
                                        DeleteFile(file);
                                        Toast.makeText(getApplicationContext(),
                                                "youshixiu文件夹已被删除",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                })
                        .setNegativeButton("取消",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        // 点击“返回”后的操作,这里不设置没有任何操作
                                        dialog.dismiss();

                                    }
                                }).create().show();

            } else {
                DeleteFile(file);
                Toast.makeText(getApplicationContext(), "youshixiu文件夹已被删除",
                        Toast.LENGTH_SHORT).show();
            }

        }

    }


    private boolean isAvilible(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        // 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        // 获取所有已安装程序的包信息
        List<String> pName = new ArrayList<String>();
        // 用于存储所有已安装程序的包名
        // 从pinfo中将包名字逐一取出，压入pName list中
        if (pName != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                pName.add(pn);
            }
        }
        return pName.contains(packageName);
        // 判断pName中是否有目标程序的包名，有TRUE，没有FALSE
    }

    // 删除test文件
    public void deletetestfile() {
        File sd = Environment.getExternalStorageDirectory();
        File f = new File(sd.getPath());
        File[] file = f.listFiles();

        for (int i = 0; i < file.length; i++) {
            if (file[i].getName().equals("test.properties")) {
                boolean a = file[i].delete();
                System.out.println(a);
                System.out.println(i);
                System.out.println(file[i].getName());
                System.out.println("ceshi文件已被删除");

            }
        }

    }

    // 判断下test文件内容是否是测试环境的配置文件
    private void isexisttestfile(String searchString) {
        Properties p = new Properties();
        try {
            p.load(new FileInputStream(new File("/sdcard/test.properties")));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Iterator itr = p.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry e = (Map.Entry) itr.next();
            System.out.println(e.getKey() + ": " + e.getValue());
            if (e.getKey().toString().indexOf(searchString) >= 0
                    || e.getValue().toString().indexOf(searchString) >= 0) {
                System.out.println("是测试的配置文件哦");
                Toast.makeText(getApplicationContext(), "已是测试环境哦",
                        Toast.LENGTH_SHORT).show();

            } else {
                deletetestfile();
            }
        }
    }
}
