package com.project.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

public class FileUtils {

    public final static String TAG =  "FileUtils";

    //缓存
    public static String file_p = ""; //图片类型
    public static String file_t = ""; //非图片类型

    public static void CreateCacheFile(Context context, String fileName) throws IOException {

        File file = new File(context.getFilesDir(),fileName);
        file_p =   file.getAbsolutePath() + "/photo";
        file_t =   file.getAbsolutePath() + "/cache";

        File photo_file = new File(file_p);
        if (!photo_file.exists()){
            photo_file.mkdirs();
        }
        File normal_file = new File(file_t);
        if (!normal_file.exists()){
            normal_file.mkdirs();
        }
    }

    /**
     * @param path   全路径 file_p + "/" + 文件名
     * @param bitmap 图片bitmap
     * @throws IOException
     */
    public static void CacheToFile(String path, Bitmap bitmap) throws IOException {
        File file = new File(path);  //创建文件
        FileOutputStream out  = new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,out);
        out.flush();
        out.close();
    }

    /**
     * 将 JSON 字符串写入到  File
     * @param path 全路径 file_t + "/" + 文件名
     * @param bytes 字节（JSON）
     * @throws IOException
     */
    public static void CacheToFile(String path, byte[] bytes) throws IOException {
        File file = new File(path);  //创建文件
        FileOutputStream out  = new FileOutputStream(file);
        out.write(bytes);
        out.flush();
        out.close();
    }

    public static String readJsonFile(String filePath)  {
        String jsonStr = "";
        try {
            File jsonFile = new File(filePath);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer stringBuffer = new StringBuffer();
            while ((ch = reader.read()) != -1){
                stringBuffer.append((char) ch);
            }
            reader.close();
            jsonStr = stringBuffer.toString();
            return jsonStr;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static boolean IsCacheExist(String path){
        File file = new File(path);
        if (file.exists()){
            return true;
        }
        return false;
    }


    /**
     * 删除文件
     * @param fileName
     * @return
     */
    public static boolean DeleteFile(String fileName){
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 删除文件夹
     * @param dir
     * @return
     */
    public static boolean DeleteDirectory(String dir){

        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            Log.d(TAG,"删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = FileUtils.DeleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = FileUtils.DeleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            Log.d(TAG,"删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            Log.d(TAG,"删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }

    /**
     * 分割httpUrl
     * return 文件名字
     * @param httpUrl
     * @return
     */
    public static String SplitHttpUrl(String httpUrl){
        if (httpUrl != null){
            String[] t = httpUrl.split("/");
            for (int i =0; i < t.length; i++){
                System.out.println(t[i]);
            }
            return t[t.length - 1];
        }
        else {
            return null;
        }

    }



}
