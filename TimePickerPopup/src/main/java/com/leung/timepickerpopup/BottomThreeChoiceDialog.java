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

import java.util.ArrayList;
import java.util.List;

/**
 * 底部圆角双选
 */
public class BottomThreeChoiceDialog {
    private final Context mContext;
    protected Dialog dialog;
    private LinearLayout mBackgroundLayout;
    private TextView mTvCancel;
    private TextView mTvTitle;
    private TextView mTvConfirm;
    private WheelView wv1;
    private WheelView wv2;
    private WheelView wv3;
    private String content1 = "";
    private String content2 = "";
    private String content3 = "";
    private List<String> list1 = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();
    private List<String> list3 = new ArrayList<>();
    private static final int MODE_1 = 1;
    private static final int MODE_2 = 2;
    private static final int MODE_3 = 3;
    private int initOnclick1 =0;
    private int initOnclick2 =0;
    private int initOnclick3 =0;


    public BottomThreeChoiceDialog(Context context) {
        this.mContext = context;
    }

    public BottomThreeChoiceDialog builder() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_bottom_three_choice_layout, null);
        mBackgroundLayout = view.findViewById(R.id.ll_background);
        mTvCancel = view.findViewById(R.id.tv_cancel);
        mTvTitle = view.findViewById(R.id.tv_title);
        mTvConfirm = view.findViewById(R.id.tv_confirm);
        wv1 = view.findViewById(R.id.wv_1);
        wv2 = view.findViewById(R.id.wv_2);
        wv3 = view.findViewById(R.id.wv_3);
        dialog = new Dialog(mContext, R.style.CentreDialogStyle);
        dialog.setContentView(view);
        mBackgroundLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return this;
    }

    public BottomThreeChoiceDialog setTitle(String title) {
        mTvTitle.setText(title);
        return this;
    }

    /***
     * 是否点击返回能够取消
     * @param cancel
     * @return
     */
    public BottomThreeChoiceDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }
    public BottomThreeChoiceDialog setInitClickItem(int data1, int data2, int data3) {
        initOnclick1=data1;
        initOnclick2=data2;
        initOnclick3=data3;
        return this;
    }

    public BottomThreeChoiceDialog setConfirmTextColor(int confirmTextColor) {
        mTvConfirm.setTextColor(confirmTextColor);
        return this;
    }

    public BottomThreeChoiceDialog setCancelTextColor(int cancelTextColor) {
        mTvCancel.setTextColor(cancelTextColor);
        return this;
    }

    /**
     * 设置是否可以取消
     *
     * @param isCancelOutside
     * @return
     */
    public BottomThreeChoiceDialog setCancelOutside(boolean isCancelOutside) {
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
    public BottomThreeChoiceDialog setPositiveButton(String text, final OnClickListener listener) {
        mTvConfirm.setText(text);
        mTvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(content1, content2, content3);
                }

                dialog.dismiss();
            }
        });
        return this;
    }

    public BottomThreeChoiceDialog setPositiveButton(final OnClickListener listener) {
        setPositiveButton("确定", listener);
        return this;
    }

    /***
     * 设置取消
     * @param text
     * @param listener
     * @return
     */
    public BottomThreeChoiceDialog setNegativeButton(String text,
                                                                             final View.OnClickListener listener) {

        mTvCancel.setText(text);
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }

    public BottomThreeChoiceDialog setNegativeButton(
            final View.OnClickListener listener) {
        setNegativeButton("取消", listener);
        return this;
    }


    public BottomThreeChoiceDialog build() {
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

    public void dissmiss() {
        dialog.dismiss();
    }

    public BottomThreeChoiceDialog setData(final List<String> data1, final List<String> data2, final List<String> data3) {
        content1 = data1.get(0);
        content2 = data2.get(0);
        content3 = data3.get(0);
        initWheelView(wv1, data1, MODE_1);
        initWheelView(wv2, data2, MODE_2);
        initWheelView(wv3, data3, MODE_3);
        return this;
    }

    public interface OnClickListener {
        void onClick(String content1, String content2, String content3);
    }

    private void initWheelView(WheelView wheelView, final List<String> dataList, final int mode) {
        ChoiceAdapter adapter = new ChoiceAdapter(dataList);
        wheelView.setAdapter(adapter);
        wheelView.setCurrentItem(0);
        wheelView.setCyclic(false);
        wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                if (mode == MODE_1) {
                    content1 = dataList.get(index);
                } else if (mode == MODE_2) {
                    content2 = dataList.get(index);
                } else {
                    content3 = dataList.get(index);
                }
            }
        });
        wheelView.setTextColorCenter(Color.parseColor("#333333"));
        wheelView.setTextColorOut(Color.parseColor("#666666"));
        wheelView.setTextSize(14);
        wheelView.setDividerColor(0);
        wheelView.setLineSpacingMultiplier(3);
        wheelView.setDividerType(WheelView.DividerType.WRAP);
        if (mode == MODE_1) {
            wheelView.setCurrentItem(initOnclick1);
        } else if (mode == MODE_2) {
            wheelView.setCurrentItem(initOnclick2);
        } else if (mode == MODE_3) {
            wheelView.setCurrentItem(initOnclick3);
        }

    }

    private class ChoiceAdapter implements WheelAdapter<String> {
        private List<String> dataList;

        public ChoiceAdapter(List<String> dataList) {
            this.dataList = dataList;
        }

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
    }
}
