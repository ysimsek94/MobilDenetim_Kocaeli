package com.dembs.android.mobildenetim.utils;

import com.dembs.android.mobildenetim.models.DenetimFormItemLine;

import java.util.ArrayList;

public interface OnItemClick {

    void onClick(ArrayList<DenetimFormItemLine> list);

    void onClickCounter(int counter);

}