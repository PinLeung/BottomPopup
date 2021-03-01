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
public class BottomDoubleChoiceDialog {
    private final Context mContext;
    protected Dialog dialog;
    private LinearLayout mBackgroundLayout;
    private TextView mTvCancel;
    private TextView mTvTitle;
    private TextView mTvConfirm;
    private WheelView wvLeft;
    private WheelView wvRight;
    private String lContent = "";
    private String rContent = "";
    private List<String> leftList = new ArrayList<>();
    private List<String> rightList = new ArrayList<>();
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private int leftPosition=0;
    private int rightPosition=0;

    public BottomDoubleChoiceDialog(Context context) {
        this.mContext = context;
    }

    public BottomDoubleChoiceDialog builder() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_bottom_double_choice_layout, null);
        mBackgroundLayout = view.findViewById(R.id.ll_background);
        mTvCancel = view.findViewById(R.id.tv_cancel);
        mTvTitle = view.findViewById(R.id.tv_title);
        mTvConfirm = view.findViewById(R.id.tv_confirm);
        wvLeft = view.findViewById(R.id.wv_left);
        wvRight = view.findViewById(R.id.wv_right);
        dialog = new Dialog(mContext, R.style.CentreDialogStyle);
        dialog.setContentView(view);
        mBackgroundLayout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return this;
    }

    public BottomDoubleChoiceDialog setTitle(String title) {
        mTvTitle.setText(title);
        return this;
    }

    public BottomDoubleChoiceDialog setposition(int leftPosition,int rightPosition ){
        this.leftPosition=leftPosition;
        this.rightPosition=rightPosition;
        return this;
    }

    /***
     * 是否点击返回能够取消
     * @param cancel
     * @return
     */
    public BottomDoubleChoiceDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    /**
     * 设置是否可以取消
     *
     * @param isCancelOutside
     * @return
     */
    public BottomDoubleChoiceDialog setCancelOutside(boolean isCancelOutside) {
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
    public BottomDoubleChoiceDialog setPositiveButton(String text, final OnClickListener listener) {
        mTvConfirm.setText(text);
        mTvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(lContent, rContent);
                }

                dialog.dismiss();
            }
        });
        return this;
    }

    public BottomDoubleChoiceDialog setPositiveButton(final OnClickListener listener) {
        setPositiveButton("确定", listener);
        return this;
    }

    /***
     * 设置取消
     * @param text
     * @param listener
     * @return
     */
    public BottomDoubleChoiceDialog setNegativeButton(String text,
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

    public BottomDoubleChoiceDialog setNegativeButton(
            final View.OnClickListener listener) {
        setNegativeButton("取消", listener);
        return this;
    }

    public BottomDoubleChoiceDialog build() {
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

    public BottomDoubleChoiceDialog setData(final List<String> leftData, final List<String> rightData) {
        lContent = leftData.get(0);
        rContent = rightData.get(0);
        initWheelView(wvLeft, leftData, LEFT);
        initWheelView(wvRight, rightData, RIGHT);
        return this;
    }

    public interface OnClickListener {
        void onClick(String lContent, String rContent);
    }

    private void initWheelView(WheelView wheelView, final List<String> dataList, final int mode) {
        ChoiceAdapter adapter = new ChoiceAdapter(dataList);
        wheelView.setAdapter(adapter);
        if (mode==LEFT){
            if (leftPosition>dataList.size()){
                leftPosition=0;
            }
            wheelView.setCurrentItem(leftPosition);
        }else {
            if (rightPosition>dataList.size()){
                rightPosition=0;
            }
            wheelView.setCurrentItem(leftPosition);
        }

        wheelView.setCyclic(false);
        wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                if (mode == LEFT) {
                    lContent = dataList.get(index);
                } else {
                    rContent = dataList.get(index);
                }
            }
        });
        wheelView.setTextColorCenter(Color.parseColor("#333333"));
        wheelView.setTextColorOut(Color.parseColor("#666666"));
        wheelView.setTextSize(14);
        wheelView.setDividerColor(0);
        wheelView.setLineSpacingMultiplier(3);
        wheelView.setDividerType(WheelView.DividerType.WRAP);
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
