package com.xuxuexia.youshixiu.easytest;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class DamiActivity extends AppCompatActivity {

    private String fileDirPath = Environment.getExternalStorageDirectory()
            .getPath()// 得到外部存储卡的数据库的路径名
            + "";// 我要存储的目录
    private String damiFileName = "damitv.properties";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dami);

        //切换到大米测试环境

        Button switchToDamiTestButton = (Button) findViewById(R.id.switchToDamiTest);
        switchToDamiTestButton.setOnClickListener(new switchtoDamitestListener());

        //切换到大米现网
        Button switchToDamiNormalButton = (Button) findViewById(R.id.switchToDamiNormal);
        switchToDamiNormalButton.setOnClickListener(new switchtonormalDamiListener());
    }


    // 切换到大米app的测试环境
    class switchtoDamitestListener implements View.OnClickListener {
        public void onClick(View v) {

            // 不管有没有test文件，先删除掉，再说，防止开发人员放了开发环境的test文件
            deleteDamitestfile();

            // 然后再放一份test文件 得到sdcard的路径 /storage/emulated/0
            // String lujingString = android.os.Environment
            // .getExternalStorageDirectory().getAbsolutePath();
            //createFileYsxTest();///bbbbb
            createFileDamiTest();
        }
    }

    // 切换到大米正式环境
    class switchtonormalDamiListener implements View.OnClickListener {
        // 删除sdcard下的配置文件
        public void onClick(View v) {
            // 不管有没有test文件，先删除掉，再说，防止开发人员放了开发环境的test文件
            deleteDamitestfile();

            Toast.makeText(getApplicationContext(), "已切换为大米正式环境了",
                    Toast.LENGTH_SHORT).show();

        }
    }

    private void createFileDamiTest() {
        String damifilePath = fileDirPath + "/" + damiFileName;// 文件路径
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
            File file = new File(damifilePath);
            if (!file.exists()) {// 文件不存在
                System.out.println("要打开的文件不存在");
                InputStream ins = getResources().openRawResource(R.raw.damitv);// 通过raw得到数据资源
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
                Toast.makeText(getApplicationContext(), "已切换为大米测试环境了",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //删除大米测试环境配置文件
    public void deleteDamitestfile() {
        File sd = Environment.getExternalStorageDirectory();
        File f = new File(sd.getPath());
        File[] file = f.listFiles();

        for (int i = 0; i < file.length; i++) {
            if (file[i].getName().equals("damitv.properties")) {
                boolean a = file[i].delete();
                System.out.println(a);
                System.out.println(i);
                System.out.println(file[i].getName());
                System.out.println("ceshi文件已被删除");

            }
        }

    }
}
