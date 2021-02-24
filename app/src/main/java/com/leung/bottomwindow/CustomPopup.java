package com.leung.bottomwindow;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lxj.xpopup.animator.PopupAnimator;
import com.lxj.xpopup.core.CenterPopupView;


class CustomPopup extends CenterPopupView {
    private TextView title;
    private TextView cancel;
    private TextView confirm;
    private OnClickListener confirmListener;
    private OnClickListener cancelListener;
    private String cancelText="取消";
    private String confirmText="确定";
    private String titleText="";
    private boolean isCancelHide=false;

    //注意：自定义弹窗本质是一个自定义View，但是只需重写一个参数的构造，其他的不要重写，所有的自定义弹窗都是这样。
    public CustomPopup(@NonNull Context context) {
        super(context);
    }

    // 返回自定义弹窗的布局
    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_popup;
    }

    // 执行初始化操作，比如：findView，设置点击，或者任何你弹窗内的业务逻辑
    @Override
    protected void onCreate() {
        super.onCreate();
        title = findViewById(R.id.tv_title);
        confirm = findViewById(R.id.tv_confirm);
        cancel = findViewById(R.id.tv_cancel);
        title.setText(titleText);
        confirm.setText(confirmText);
        if (isCancelHide){
            cancel.setVisibility(GONE);
        }else {
            cancel.setText(cancelText);
            cancel.setOnClickListener(v -> {
                if (cancelListener != null) {
                    cancelListener.onClick(v);
                    CustomPopup.this.dismiss();
                }
            });
        }
        confirm.setOnClickListener(v -> {
            if (confirmListener != null) {
                confirmListener.onClick(v);
                CustomPopup.this.dismiss();
            }
        });

    }

    public CustomPopup setCancelText(String cancelText) {
        this.cancelText = cancelText;
        return this;
    }

    public CustomPopup setConfirmText(String confirmText) {
        this.confirmText = confirmText;
        return this;
    }

    public CustomPopup setTitleText(String titleText) {
        this.titleText = titleText;
        return this;
    }

    public CustomPopup setConfirmListener(OnClickListener confirmListener) {
        this.confirmListener = confirmListener;
        return this;
    }

    public CustomPopup setCancelListener(OnClickListener cancelListener) {
        this.cancelListener = cancelListener;
        return this;
    }

    public CustomPopup setHide(boolean hide) {
        isCancelHide = hide;
        return this;
    }
}