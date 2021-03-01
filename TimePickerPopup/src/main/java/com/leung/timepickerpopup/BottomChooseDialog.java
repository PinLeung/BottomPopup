package com.leung.timepickerpopup;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.contrarywind.adapter.WheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;

import java.util.List;

public class BottomChooseDialog {
    private final Context mContext;
    protected Dialog dialog;
    private LinearLayout mBackgroundLayout;
    private TextView mTvCancel;
    private TextView mTvTitle;
    private TextView mContent;
    private TextView mTvConfirm;
    private WheelView wheelView;
    private String content="";
    private int position=0;

    public BottomChooseDialog(Context context) {
        this.mContext = context;
    }

    public BottomChooseDialog builder() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_bottom_choose_layout, null);
        mBackgroundLayout = view.findViewById(R.id.ll_background);
        mTvCancel = view.findViewById(R.id.tv_cancel);
        mTvTitle = view.findViewById(R.id.tv_title);
        mTvConfirm = view.findViewById(R.id.tv_confirm);
        mContent=view.findViewById(R.id.tv_content);
        wheelView = view.findViewById(R.id.wheelView);
        dialog = new Dialog(mContext, R.style.CentreDialogStyle);
        dialog.setContentView(view);
        mBackgroundLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return this;
    }

    public BottomChooseDialog setTitle(String title) {
        mTvTitle.setText(title);
        return this;
    }

    /***
     * 是否点击返回能够取消
     * @param cancel
     * @return
     */
    public BottomChooseDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    /**
     * 设置是否可以取消
     *
     * @param isCancelOutside
     * @return
     */
    public BottomChooseDialog setCancelOutside(boolean isCancelOutside) {
        dialog.setCanceledOnTouchOutside(isCancelOutside);
        return this;
    }

    /**
     * 设置确定
     *
     * @param text
     * @param listener
     * @return
     */
    public BottomChooseDialog setPositiveButton(String text, final OnClickListener listener) {
        mTvConfirm.setText(text);
        mTvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(position,content);
                dialog.dismiss();
            }
        });
        return this;
    }

    public BottomChooseDialog setPositiveButton(final OnClickListener listener) {
        setPositiveButton("确定", listener);
        return this;
    }

    public BottomChooseDialog setConfirmTextColor(int confirmTextColor) {
        mTvConfirm.setTextColor(confirmTextColor);
        return this;
    }

    public BottomChooseDialog setCancelTextColor(int cancelTextColor) {
        mTvCancel.setTextColor(cancelTextColor);
        return this;
    }

    public BottomChooseDialog setContent(String s) {
        mContent.setText(s);
        mContent.setVisibility(View.VISIBLE);
        return this;
    }

    /***
     * 设置取消
     * @param text
     * @param listener
     * @return
     */
    public BottomChooseDialog setNegativeButton(String text,final View.OnClickListener listener) {

        mTvCancel.setText(text);
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null) {
                    listener.onClick(v);
                }
                dialog.dismiss();
            }
        });
        return this;
    }

    public BottomChooseDialog setNegativeButton(
            final View.OnClickListener listener) {
        setNegativeButton("取消", listener);
        return this;
    }

    public BottomChooseDialog setposition(int position){
        this.position=position;
        return this;
    }

    public void show() {
        Window win = dialog.getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        win.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        win.setGravity(Gravity.BOTTOM);
        win.setAttributes(lp);
        dialog.show();
    }



    public BottomChooseDialog setData(final List<String> dataList) {
        content = dataList.get(0);
        WheelAdapter<String> adapter = new WheelAdapter<String>() {
            @Override
            public int getItemsCount() {
                return dataList == null ? 0 : dataList.size();
            }

            @Override
            public String getItem(int index) {
                return dataList.get(index);
            }

            @Override
            public int indexOf(String o) {
                return dataList.indexOf(o);
            }
        };
        wheelView.setAdapter(adapter);
        if (position>dataList.size()){
            wheelView.setCurrentItem(0);
            position=0;
        }else {
            wheelView.setCurrentItem(position);
        }

        wheelView.setCyclic(false);
        wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                content = dataList.get(index);
                position=index;
            }
        });
        wheelView.setTextColorCenter(Color.parseColor("#333333"));
        wheelView.setTextColorOut(Color.parseColor("#666666"));
        wheelView.setTextSize(14);
        wheelView.setDividerColor(0);
        wheelView.setLineSpacingMultiplier(3);
        wheelView.setDividerType(WheelView.DividerType.WRAP);
        return this;
    }
    public interface OnClickListener{
        void onClick(int position, String content);
    }
}
