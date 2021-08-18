package com.project.common.dialog.date;

import android.content.Context;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateDialog {

    private CustomDatePicker mTimePicker;
    private Context mContext;

    public DateDialog(Context context,TextView tv) {

        this.mContext = context;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String time = sdf.format(new Date());
        mTimePicker = new CustomDatePicker(mContext, "请选择时间", new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) {
                tv.setText(time);
            }
        }, "2007-01-01 00:00", "2027-12-31 23:59");//"2027-12-31 23:59"
        mTimePicker.showSpecificTime(true);
        mTimePicker.setIsLoop(true);
        mTimePicker.show(time);
    }
}
