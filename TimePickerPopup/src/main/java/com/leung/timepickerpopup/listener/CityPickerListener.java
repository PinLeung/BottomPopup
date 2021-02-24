package com.leung.timepickerpopup.listener;

import android.view.View;

public interface CityPickerListener {
    void onCityConfirm(String province, String city, String area, View v);
    void onCityChange(String province, String city, String area);

}
