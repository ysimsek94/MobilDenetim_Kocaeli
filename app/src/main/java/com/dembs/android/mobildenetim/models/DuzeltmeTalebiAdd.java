package com.dembs.android.mobildenetim.models;

import java.util.ArrayList;
import java.util.List;

public class DuzeltmeTalebiAdd {


    private int denetimId;
    private List<DuzeltmeTalebiLineAdd>DuzeltmeTalebList=new ArrayList<>();


    public int getDenetimId() {
        return denetimId;
    }

    public void setDenetimId(int denetimId) {
        this.denetimId = denetimId;
    }

    public List<DuzeltmeTalebiLineAdd> getDuzeltmeTalebiList() {
        return DuzeltmeTalebList;
    }

    public void setDuzeltmeTalebiList(List<DuzeltmeTalebiLineAdd> duzeltmeTalebiList) {
        this.DuzeltmeTalebList = duzeltmeTalebiList;
    }

}
