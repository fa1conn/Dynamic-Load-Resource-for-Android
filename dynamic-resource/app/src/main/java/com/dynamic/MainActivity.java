package com.dynamic;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.io.File;
import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private SkinManager mSkinManager;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSkinManager = new SkinManager();
        iv = findViewById(R.id.image_view);
        checkNewSkin();
    }

    @SuppressLint("ResourceType")
    private void checkNewSkin() {
        //app所在目录：/storage/emulated/0/app-debug.apk
        String dexPath = Environment.getExternalStorageDirectory().toString() + File.separator + "app-debug.apk";

        //File pluginFile = new File(dexPath);
        //Resources skinResource = SkinManager.getSkinResource(this, pluginFile.getAbsolutePath());

        //获取apk资源
        Resources skinResource = SkinManager.getSkinResource(this, dexPath);
        if (skinResource != null) {
            String PackageName = SkinManager.getPackageName(this, dexPath);
            /*
            PackageManager mPm = this.getPackageManager();
            PackageInfo mInfo = mPm.getPackageArchiveInfo(dexPath, PackageManager.GET_ACTIVITIES);
            String PackageName = mInfo.packageName;
            */

            int skinResourceId = SkinManager.getSkinResorceId(this,
                    skinResource,
                    R.drawable.test,
                    PackageName,
                    SkinManager.DRAWABLE);
            iv.setImageDrawable(skinResource.getDrawable(skinResourceId));
        }
    }
}
