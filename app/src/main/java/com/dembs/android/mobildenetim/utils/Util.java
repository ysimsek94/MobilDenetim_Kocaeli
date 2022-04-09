package com.dembs.android.mobildenetim.utils;

import android.text.InputType;
import android.widget.EditText;

public final class Util {

    public static void setDisableEditText(EditText editText){
        editText.setFocusable(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
    }
    public static void setEnabledEditText(EditText editText){
        editText.setEnabled(true);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        editText.setFocusable(true);
    }
}
