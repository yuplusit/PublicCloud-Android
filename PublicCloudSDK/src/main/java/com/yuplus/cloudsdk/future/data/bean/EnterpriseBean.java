package com.yuplus.cloudsdk.future.data.bean;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseBean;

/**
 * @user longzhen
 * @date 5/13/2017
 * @desc
 */

public class EnterpriseBean extends BaseBean {

    private String enterpriseID;
    private String domainID;
    private String domainPath;
    private String name;
    private String shortName;
    private String description;
    private String userID;
    private String roleID;
    private String duration;
    private String enterpriseSize;
    private int    enterpriseType;
    private int    enterpriseStatus;
    private String enterpriseLogo;
    private String province;
    private String city;
    private String district;
    private String address;
    private String officePhone;
    private String contactPhone;
    private String contactEmail;
    private String contact;
    private String website;
    private String business;
    private String copyRight;
    private String websiteUrl;
    private String defaultRole;

    public String getEnterpriseID() {
        return enterpriseID;
    }

    public void setEnterpriseID(String enterpriseID) {
        this.enterpriseID = enterpriseID;
    }

    public String getDomainID() {
        return domainID;
    }

    public void setDomainID(String domainID) {
        this.domainID = domainID;
    }

    public String getDomainPath() {
        return domainPath;
    }

    public void setDomainPath(String domainPath) {
        this.domainPath = domainPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEnterpriseSize() {
        return enterpriseSize;
    }

    public void setEnterpriseSize(String enterpriseSize) {
        this.enterpriseSize = enterpriseSize;
    }

    public int getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(int enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public int getEnterpriseStatus() {
        return enterpriseStatus;
    }

    public void setEnterpriseStatus(int enterpriseStatus) {
        this.enterpriseStatus = enterpriseStatus;
    }

    public String getEnterpriseLogo() {
        return enterpriseLogo;
    }

    public void setEnterpriseLogo(String enterpriseLogo) {
        this.enterpriseLogo = enterpriseLogo;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getCopyRight() {
        return copyRight;
    }

    public void setCopyRight(String copyRight) {
        this.copyRight = copyRight;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getDefaultRole() {
        return defaultRole;
    }

    public void setDefaultRole(String defaultRole) {
        this.defaultRole = defaultRole;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.enterpriseID);
        dest.writeString(this.domainID);
        dest.writeString(this.domainPath);
        dest.writeString(this.name);
        dest.writeString(this.shortName);
        dest.writeString(this.description);
        dest.writeString(this.userID);
        dest.writeString(this.roleID);
        dest.writeString(this.duration);
        dest.writeString(this.enterpriseSize);
        dest.writeInt(this.enterpriseType);
        dest.writeInt(this.enterpriseStatus);
        dest.writeString(this.enterpriseLogo);
        dest.writeString(this.province);
        dest.writeString(this.city);
        dest.writeString(this.district);
        dest.writeString(this.address);
        dest.writeString(this.officePhone);
        dest.writeString(this.contactPhone);
        dest.writeString(this.contactEmail);
        dest.writeString(this.contact);
        dest.writeString(this.website);
        dest.writeString(this.business);
        dest.writeString(this.copyRight);
        dest.writeString(this.websiteUrl);
        dest.writeString(this.defaultRole);
    }

    public EnterpriseBean() {
    }

    protected EnterpriseBean(Parcel in) {
        this.enterpriseID = in.readString();
        this.domainID = in.readString();
        this.domainPath = in.readString();
        this.name = in.readString();
        this.shortName = in.readString();
        this.description = in.readString();
        this.userID = in.readString();
        this.roleID = in.readString();
        this.duration = in.readString();
        this.enterpriseSize = in.readString();
        this.enterpriseType = in.readInt();
        this.enterpriseStatus = in.readInt();
        this.enterpriseLogo = in.readString();
        this.province = in.readString();
        this.city = in.readString();
        this.district = in.readString();
        this.address = in.readString();
        this.officePhone = in.readString();
        this.contactPhone = in.readString();
        this.contactEmail = in.readString();
        this.contact = in.readString();
        this.website = in.readString();
        this.business = in.readString();
        this.copyRight = in.readString();
        this.websiteUrl = in.readString();
        this.defaultRole = in.readString();
    }

    public static final Creator<EnterpriseBean> CREATOR = new Creator<EnterpriseBean>() {
        @Override
        public EnterpriseBean createFromParcel(Parcel source) {
            return new EnterpriseBean(source);
        }

        @Override
        public EnterpriseBean[] newArray(int size) {
            return new EnterpriseBean[size];
        }
    };
}
