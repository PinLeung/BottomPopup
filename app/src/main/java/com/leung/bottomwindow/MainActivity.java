package com.leung.bottomwindow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.leung.timepickerpopup.listener.TimePickerListener;
import com.leung.timepickerpopup.TimePickerPopup;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.LoadingPopupView;
import com.lxj.xpopup.interfaces.OnSelectListener;


import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1 = this.findViewById(R.id.button1);
        Button b2 = this.findViewById(R.id.button2);
        Button b3 = this.findViewById(R.id.button3);
        Button b4 = this.findViewById(R.id.button4);
        Button b5 = this.findViewById(R.id.button5);


        CustomPopup customPopup = new CustomPopup(this)
                .setConfirmListener(v -> { })
                .setTitleText("我是标题")
                .setHide(true);

        b1.setOnClickListener(v -> {
            new XPopup.Builder(this)
                    .asCustom(customPopup)
                    .show();
        });
//        customPopup.setNegativeButton("asfd",v -> {
//            Toast.makeText(MainActivity.this, "选择的时间：", Toast.LENGTH_SHORT).show();
//        });

        b2.setOnClickListener(v -> {
            LoadingPopupView xPopup = new XPopup.Builder(this)
                    .asLoading("正在加载中");
            xPopup.show();
            xPopup.delayDismiss(2000);
        });

        b3.setOnClickListener(v -> {
            new XPopup.Builder(this)
                    .asBottomList("请选择一项", new String[]{"条目1", "条目2", "条目3", "条目4", "条目5"},
                            new OnSelectListener() {
                                @Override
                                public void onSelect(int position, String text) {

                                }
                            })
                    .show();
        });

        b4.setOnClickListener(v -> {

            new XPopup.Builder(this)
                    .atView(v)  // 依附于所点击的View，内部会自动判断在上方或者下方显示
                    .asCustom(new CustomPartShadowPopupView(this))
                    .show();
        });


        b5.setOnClickListener(v -> {
            Calendar date = Calendar.getInstance();
            //月份从0开始的，传5表示6月份
            date.set(2000, 5, 1);
            Calendar date2 = Calendar.getInstance();
            date2.set(2020, 5, 1);
            TimePickerPopup popup = new TimePickerPopup(MainActivity.this)
                        .setDefaultDate(date)  //设置默认选中日期
                    //传-1表示不设置值
//                        .setYearRange(1990, -1) //设置年份范围
                    //传空表示不设置值
                        .setDateRang(date, null) //设置日期范围
                    .setTopText("修改标题")
                    .setLeftText("修改取消")
                    .setRightText("修改确定")
                    .setTimePickerListener(new TimePickerListener() {
                        @Override
                        public void onTimeChanged(Date date) {
                            //时间改变
                        }
                        @Override
                        public void onTimeConfirm(Date date, View view) {
                            //点击确认时间
                            Toast.makeText(MainActivity.this, "选择的时间：" + date.toLocaleString(), Toast.LENGTH_SHORT).show();
                        }
                    });
            new XPopup.Builder(MainActivity.this)
                    .asCustom(popup)
                    .show();
        });
    }

}