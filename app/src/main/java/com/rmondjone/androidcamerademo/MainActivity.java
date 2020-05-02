package com.rmondjone.androidcamerademo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.rmondjone.camera.CameraActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static  final int TAKE_PHOTO = 2005;
    private String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 注释：跳转拍照
     * 时间：2019/3/21 0021 9:13
     * 作者：郭翰林
     */
    public void gotoCamare(View view) {
        CameraActivity.startMe(this,  TAKE_PHOTO, CameraActivity.MongolianLayerType.IDCARD_POSITIVE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode== TAKE_PHOTO && resultCode == RESULT_OK)
        {
            String imagePath = data.getStringExtra(CameraActivity.KEY_IMAGE_PATH);
            File image = new File(imagePath);
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(),bmOptions);
            Log.e("Alading", "Get a bitmap size =  " +bitmap.getWidth() + "," +bitmap.getHeight());

        }
    }



}
