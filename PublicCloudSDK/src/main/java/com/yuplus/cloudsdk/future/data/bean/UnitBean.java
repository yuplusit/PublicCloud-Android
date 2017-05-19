package com.yuplus.cloudsdk.future.data.bean;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseBean;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public class UnitBean extends BaseBean {

    private String unitCode;
    private String unitName;
    private double factor;
    private String basicFormat;
    private String baseUnit;
    private String format;

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public double getFactor() {
        return factor;
    }

    public void setFactor(double factor) {
        this.factor = factor;
    }

    public String getBasicFormat() {
        return basicFormat;
    }

    public void setBasicFormat(String basicFormat) {
        this.basicFormat = basicFormat;
    }

    public String getBaseUnit() {
        return baseUnit;
    }

    public void setBaseUnit(String baseUnit) {
        this.baseUnit = baseUnit;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.unitCode);
        dest.writeString(this.unitName);
        dest.writeDouble(this.factor);
        dest.writeString(this.basicFormat);
        dest.writeString(this.baseUnit);
        dest.writeString(this.format);
    }

    public UnitBean() {
    }

    protected UnitBean(Parcel in) {
        this.unitCode = in.readString();
        this.unitName = in.readString();
        this.factor = in.readDouble();
        this.basicFormat = in.readString();
        this.baseUnit = in.readString();
        this.format = in.readString();
    }

    public static final Creator<UnitBean> CREATOR = new Creator<UnitBean>() {
        @Override
        public UnitBean createFromParcel(Parcel source) {
            return new UnitBean(source);
        }

        @Override
        public UnitBean[] newArray(int size) {
            return new UnitBean[size];
        }
    };
}
