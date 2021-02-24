一、如何导入依赖
1. 添加仓库
```
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
     }
```
   
2.导入依赖
 ```
      dependencies {

       ......
       implementation 'com.lxj:xpopup:2.0.2'
        implementation 'com.github.PinLeung:BottomPopup:V1.0.0'
       }
 ```

二、如何使用
```
                 bottom.setOnClickListener(v -> {
                   Calendar date = Calendar.getInstance();
                  //月份从0开始的，传5表示6月份
                    date.set(2000, 5, 1);
                    TimePickerPopup popup = new TimePickerPopup(MainActivity.this)
                          //设置默认选中时间
                          .setDefaultDate(date)  
                         .setTimePickerListener(new TimePickerListener() {
                           @Override
                           public void onTimeChanged(Date date) {
                              //时间改变
                           }
                           @Override
                           public void onTimeConfirm(Date date, View view) {
                            //点击确认时间
                            //返回一个Date对象
                               Toast.makeText(MainActivity.this, "选择的时间：" + date.toLocaleString(), Toast.LENGTH_SHORT).show();
                           }
                       });
                 new XPopup.Builder(MainActivity.this)
                      .asCustom(popup)
                     .show();
             });
```
         
三、扩展

  ```
       1.setYearRange(1990, -1)//设置年份范围，传入的范围是1990-2100，-1表示其中最大值或者最小值
       2.setDateRang（data，data）//传入时间范围，null表示最大值
       3.setDialogBackground(Drawable drawable)//设置背景
       4.setItemTextSize(int textSize)//设置滑动的文字大小
       6.setTopText(String topText) //设置小标题，默认不显示
       7.setTopText(String topText,int topTextColor)//设置小标题，及颜色，默认灰色0xFFa8a8a8
       8.setTopTextSize(int topTextSize)//设置小标题颜色
       9.setMode(Mode mode)//设置显示的模式， YMDHMS, YMDHM, YMDH, YMD, YM, Y  默认年月日YMD
       10.setLunar(boolean isLunar)是否是农历
       11.setDefaultDate(Calendar date)//设置默认时间
       12.setLeftText(String leftText)//设置左边文字，默认取消
       13.setRightText(String rightText)//设置右边文字，默认确定
       14.setRightTextColor(int rightTextColor)//设置右边文字颜色//默认蓝色0xFF38A6FF
       15.setRightText(String rightText,int rightTextColor)//设置右边文字及颜色
    ```
