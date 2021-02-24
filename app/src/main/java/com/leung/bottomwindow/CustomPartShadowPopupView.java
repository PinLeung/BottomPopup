package com.leung.bottomwindow;

import android.content.Context;

import androidx.annotation.NonNull;

import com.lxj.xpopup.impl.PartShadowPopupView;


public class CustomPartShadowPopupView extends PartShadowPopupView {
    public CustomPartShadowPopupView(@NonNull Context context) {
        super(context);
    }
    @Override
    protected int getImplLayoutId() {
        return R.layout.demo; // 编写你自己的布局
    }
    @Override
    protected void onCreate() {
        super.onCreate();
        // 实现一些UI的初始和逻辑处理
    }
}