package com.project.common;

/**
 * 已经登录的用户信息
 * 全局
 */
public class LoginUserInfo {

    public static String User_id   = "";

    public static String User_name = "";

    public static String Img_path  = "";// Glide已经做了缓存   //图片id（MD5）  //从服务器获取图片ID，首先判断数据库有没有这张图片，没有则从服务器获取


}