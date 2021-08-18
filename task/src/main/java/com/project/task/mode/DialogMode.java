package com.project.task.mode;


import com.project.task.ui.activity.ErrorDevActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Task模块用
 */
public class DialogMode {

    private List<String> Station    = new ArrayList<>();
    private List<String> Dev        = new ArrayList<>();
    private List<String> ErrorType  = new ArrayList<>();
    private List<String> ErrorLevel = new ArrayList<>();

    private List<String> PictureMode = new ArrayList<>();

    public List<String> getStation() {
        Station.add("东莞火车站");
        Station.add("茶山站");
        Station.add("榴花公园站");
        Station.add("下桥站");
        Station.add("天宝站");
        Station.add("东城站");
        Station.add("旗峰公园站");
        Station.add("鸿福路");
        Station.add("西平站");
        Station.add("蛤地站");
        Station.add("陈屋站");
        Station.add("寮厦站");
        Station.add("珊美站");
        Station.add("展览中心站");
        Station.add("虎门火车站");
        return Station;
    }

    public List<String> getDev() {
        Dev.add("BOM");
        Dev.add("AGM");
        Dev.add("TVM");
        Dev.add("TCM");
        return Dev;
    }

    public List<String> getErrorType() {
        ErrorType.add("扇门故障");
        ErrorType.add("电池故障");
        ErrorType.add("读写器故障");
        ErrorType.add("通讯故障");
        return ErrorType;
    }

    public List<String> getErrorLevel() {
        ErrorLevel.add("一级");
        ErrorLevel.add("二级");
        ErrorLevel.add("三级");
        ErrorLevel.add("四级");
        return ErrorLevel;
    }


    public List<String> getPictureMode() {
        PictureMode.add("拍照");
        PictureMode.add("从相册中获取");
        return PictureMode;
    }
}
