package com.project.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;
import androidx.core.os.EnvironmentCompat;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CameraUtils {

    public static final int TAKE_PHOTO      = 1;   //照相
    public static final int SCAN_OPEN_PHONE = 2;   // 相册

    //用于保存拍照图片的uri
    public static Uri mCameraUri;

    // 用于保存图片的文件路径，Android 10以下使用图片路径访问图片
    public static String mCameraImagePath;


    public static void openGallery(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        activity.startActivityForResult(intent,SCAN_OPEN_PHONE);
    }

    public static void openCamera(Activity activity){
        // 是否是Android 10以上手机
        boolean isAndroidQ = Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q;
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断是否有相机
        if (captureIntent.resolveActivity(activity.getPackageManager()) != null){
            File photoFile = null;
            Uri photoUri = null;

            if (isAndroidQ){
                //适配Android 10
                photoUri = createImageUri(activity);
            }else {
                try {
                    photoFile = createImageFile(activity);
                }catch (IOException e){
                    e.printStackTrace();
                }
                if (photoFile != null){
                    mCameraImagePath = photoFile.getAbsolutePath();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                        //适配Android 7.0文件权限，通过FileProvider创建一个content类型的Uri
                        photoUri = FileProvider.getUriForFile(activity, activity.getPackageName() + ".fileProvider", photoFile);
                    }else {
                        photoUri = Uri.fromFile(photoFile);
                    }
                }
            }
            mCameraUri = photoUri;
            if (photoUri != null) {
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                captureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                activity.startActivityForResult(captureIntent, TAKE_PHOTO);
            }
        }
    }



    /**
     * 创建图片地址uri,用于保存拍照后的照片 Android 10以后使用这种方法
     */

    public static Uri createImageUri(Activity activity){
        String status = Environment.getExternalStorageState();
        //判断是否有SD卡，优先使用SD卡存储，当没用SD卡时候使用手机存储
        if (status.equals(Environment.MEDIA_MOUNTED)){
            return activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());
        } else {
            return activity.getContentResolver().insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, new ContentValues());
        }
    }

    /**
     * 创建保存图片的文件
     */
    private static File createImageFile(Activity activity) throws IOException {
        String imageName = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File storageDir =activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (!storageDir.exists()) {
            storageDir.mkdir();
        }
        File tempFile = new File(storageDir, imageName);
        if (!Environment.MEDIA_MOUNTED.equals(EnvironmentCompat.getStorageState(tempFile))) {
            return null;
        }
        return tempFile;
    }

    public static String getRealFilePath(final Context context, final Uri uri ) {

        if ( null == uri ) return null;

        final String scheme = uri.getScheme();
        String data = null;

        if ( scheme == null )
            data = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return data;
    }



}

