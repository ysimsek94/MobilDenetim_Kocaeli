package com.dembs.android.mobildenetim.models;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class DenetimForm implements Serializable, Parcelable
{

    @SerializedName("formId")
    @Expose
    private int formId;
    @SerializedName("formAdi")
    @Expose
    private String formAdi;
    @SerializedName("formItemId")
    @Expose
    private int formItemId;
    @SerializedName("formItemAdi")
    @Expose
    private String formItemAdi;
    @SerializedName("formGrupId")
    @Expose
    private int formGrupId;
    @SerializedName("formGrupAdi")
    @Expose
    private String formGrupAdi;
    @SerializedName("ihlalMaddeId")
    @Expose
    private int ihlalMaddeId;
    @SerializedName("ihlalBendId")
    @Expose
    private int ihlalBendId;
    @SerializedName("ihlalMaddeAdi")
    @Expose
    private String ihlalMaddeAdi;
    @SerializedName("ihlalBendAdi")
    @Expose
    private String ihlalBendAdi;
    @SerializedName("secim")
    @Expose
    private boolean secim;
    @SerializedName("varaka")
    @Expose
    private boolean varaka;
    public final static Creator<DenetimForm> CREATOR = new Creator<DenetimForm>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DenetimForm createFromParcel(android.os.Parcel in) {
            return new DenetimForm(in);
        }

        public DenetimForm[] newArray(int size) {
            return (new DenetimForm[size]);
        }

    }
            ;
    private final static long serialVersionUID = 7661767652337706533L;

    protected DenetimForm(android.os.Parcel in) {
        this.formId = ((int) in.readValue((int.class.getClassLoader())));
        this.formAdi = ((String) in.readValue((String.class.getClassLoader())));
        this.formItemId = ((int) in.readValue((int.class.getClassLoader())));
        this.formItemAdi = ((String) in.readValue((String.class.getClassLoader())));
        this.formGrupId = ((int) in.readValue((int.class.getClassLoader())));
        this.formGrupAdi = ((String) in.readValue((String.class.getClassLoader())));
        this.ihlalMaddeId = ((int) in.readValue((int.class.getClassLoader())));
        this.ihlalBendId = ((int) in.readValue((int.class.getClassLoader())));
        this.ihlalMaddeAdi = ((String) in.readValue((String.class.getClassLoader())));
        this.ihlalBendAdi = ((String) in.readValue((String.class.getClassLoader())));
        this.secim = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.varaka = ((boolean) in.readValue((boolean.class.getClassLoader())));
    }

    public DenetimForm() {
    }

    public DenetimForm(int formId, String formAdi, int formItemId, String formItemAdi, int formGrupId, String formGrupAdi, int ihlalMaddeId, int ihlalBendId, String ihlalMaddeAdi, String ihlalBendAdi, boolean secim, boolean varaka) {
        this.formId = formId;
        this.formAdi = formAdi;
        this.formItemId = formItemId;
        this.formItemAdi = formItemAdi;
        this.formGrupId = formGrupId;
        this.formGrupAdi = formGrupAdi;
        this.ihlalMaddeId = ihlalMaddeId;
        this.ihlalBendId = ihlalBendId;
        this.ihlalMaddeAdi = ihlalMaddeAdi;
        this.ihlalBendAdi = ihlalBendAdi;
        this.secim = secim;
        this.varaka = varaka;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    public String getFormAdi() {
        return formAdi;
    }

    public void setFormAdi(String formAdi) {
        this.formAdi = formAdi;
    }

    public int getFormItemId() {
        return formItemId;
    }

    public void setFormItemId(int formItemId) {
        this.formItemId = formItemId;
    }

    public String getFormItemAdi() {
        return formItemAdi;
    }

    public void setFormItemAdi(String formItemAdi) {
        this.formItemAdi = formItemAdi;
    }

    public int getFormGrupId() {
        return formGrupId;
    }

    public void setFormGrupId(int formGrupId) {
        this.formGrupId = formGrupId;
    }

    public String getFormGrupAdi() {
        return formGrupAdi;
    }

    public void setFormGrupAdi(String formGrupAdi) {
        this.formGrupAdi = formGrupAdi;
    }

    public int getIhlalMaddeId() {
        return ihlalMaddeId;
    }

    public void setIhlalMaddeId(int ihlalMaddeId) {
        this.ihlalMaddeId = ihlalMaddeId;
    }

    public int getIhlalBendId() {
        return ihlalBendId;
    }

    public void setIhlalBendId(int ihlalBendId) {
        this.ihlalBendId = ihlalBendId;
    }

    public String getIhlalMaddeAdi() {
        return ihlalMaddeAdi;
    }

    public void setIhlalMaddeAdi(String ihlalMaddeAdi) {
        this.ihlalMaddeAdi = ihlalMaddeAdi;
    }

    public String getIhlalBendAdi() {
        return ihlalBendAdi;
    }

    public void setIhlalBendAdi(String ihlalBendAdi) {
        this.ihlalBendAdi = ihlalBendAdi;
    }

    public boolean isSecim() {
        return secim;
    }

    public void setSecim(boolean secim) {
        this.secim = secim;
    }

    public boolean isVaraka() {
        return varaka;
    }

    public void setVaraka(boolean varaka) {
        this.varaka = varaka;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(formId);
        dest.writeValue(formAdi);
        dest.writeValue(formItemId);
        dest.writeValue(formItemAdi);
        dest.writeValue(formGrupId);
        dest.writeValue(formGrupAdi);
        dest.writeValue(ihlalMaddeId);
        dest.writeValue(ihlalBendId);
        dest.writeValue(ihlalMaddeAdi);
        dest.writeValue(ihlalBendAdi);
        dest.writeValue(secim);
        dest.writeValue(varaka);
    }

    public int describeContents() {
        return 0;
    }

}