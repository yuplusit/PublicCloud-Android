package com.yuplus.cloudsdk.future.data.bean;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseBean;

import java.util.List;

/**
 * @user longzhen
 * @date 5/13/2017
 * @desc
 */

public class UserBean extends BaseBean {

    private String       userID;
    private String       enterpriseID;
    private String       roleID;
    private String       userType;
    private String       loginName;
    private String       userName;
    private String       createDate;
    private String         creator;
    private String       emailAddress;
    private int          status;
    private int          flag;
    private String       lastLoginTime;
    private int          loginTimes;
    private String       mobilePhone;
    private String       officePhone;
    private String       description;
    private String       updateDate;
    private long         updator;
    private String       countryRegion;
    private int          industry;
    private String         domainID;
    private String       domainPath;
    private String       address;
    private String       city;
    private String       province;
    private String       website;
    private String       business;
    private int          isDemo;
    private String       domains;
    private String       subDomain;
    private String       enterpriseDomain;
    private String       enterpriseName;
    private List<String> userDomainPathList;
    private List<String> functionCodeSet;

    private EnterpriseBean enterprise;
    private List<UserDomainBean> userDomainList;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEnterpriseID() {
        return enterpriseID;
    }

    public void setEnterpriseID(String enterpriseID) {
        this.enterpriseID = enterpriseID;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(int loginTimes) {
        this.loginTimes = loginTimes;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public long getUpdator() {
        return updator;
    }

    public void setUpdator(long updator) {
        this.updator = updator;
    }

    public Object getCountryRegion() {
        return countryRegion;
    }

    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }

    public int getIndustry() {
        return industry;
    }

    public void setIndustry(int industry) {
        this.industry = industry;
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

    public Object getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Object getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Object getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Object getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public int getIsDemo() {
        return isDemo;
    }

    public void setIsDemo(int isDemo) {
        this.isDemo = isDemo;
    }

    public String getDomains() {
        return domains;
    }

    public void setDomains(String domains) {
        this.domains = domains;
    }

    public Object getSubDomain() {
        return subDomain;
    }

    public void setSubDomain(String subDomain) {
        this.subDomain = subDomain;
    }

    public String getEnterpriseDomain() {
        return enterpriseDomain;
    }

    public void setEnterpriseDomain(String enterpriseDomain) {
        this.enterpriseDomain = enterpriseDomain;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public List<String> getUserDomainPathList() {
        return userDomainPathList;
    }

    public void setUserDomainPathList(List<String> userDomainPathList) {
        this.userDomainPathList = userDomainPathList;
    }

    public List<String> getFunctionCodeSet() {
        return functionCodeSet;
    }

    public void setFunctionCodeSet(List<String> functionCodeSet) {
        this.functionCodeSet = functionCodeSet;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public EnterpriseBean getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(EnterpriseBean enterprise) {
        this.enterprise = enterprise;
    }

    public List<UserDomainBean> getUserDomainList() {
        return userDomainList;
    }

    public void setUserDomainList(List<UserDomainBean> userDomainList) {
        this.userDomainList = userDomainList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userID);
        dest.writeString(this.enterpriseID);
        dest.writeString(this.roleID);
        dest.writeString(this.userType);
        dest.writeString(this.loginName);
        dest.writeString(this.userName);
        dest.writeString(this.createDate);
        dest.writeString(this.creator);
        dest.writeString(this.emailAddress);
        dest.writeInt(this.status);
        dest.writeInt(this.flag);
        dest.writeString(this.lastLoginTime);
        dest.writeInt(this.loginTimes);
        dest.writeString(this.mobilePhone);
        dest.writeString(this.officePhone);
        dest.writeString(this.description);
        dest.writeString(this.updateDate);
        dest.writeLong(this.updator);
        dest.writeString(this.countryRegion);
        dest.writeInt(this.industry);
        dest.writeString(this.domainID);
        dest.writeString(this.domainPath);
        dest.writeString(this.address);
        dest.writeString(this.city);
        dest.writeString(this.province);
        dest.writeString(this.website);
        dest.writeString(this.business);
        dest.writeInt(this.isDemo);
        dest.writeString(this.domains);
        dest.writeString(this.subDomain);
        dest.writeString(this.enterpriseDomain);
        dest.writeString(this.enterpriseName);
        dest.writeStringList(this.userDomainPathList);
        dest.writeStringList(this.functionCodeSet);
        dest.writeParcelable(this.enterprise, flags);
        dest.writeTypedList(this.userDomainList);
    }

    public UserBean() {
    }

    protected UserBean(Parcel in) {
        this.userID = in.readString();
        this.enterpriseID = in.readString();
        this.roleID = in.readString();
        this.userType = in.readString();
        this.loginName = in.readString();
        this.userName = in.readString();
        this.createDate = in.readString();
        this.creator = in.readString();
        this.emailAddress = in.readString();
        this.status = in.readInt();
        this.flag = in.readInt();
        this.lastLoginTime = in.readString();
        this.loginTimes = in.readInt();
        this.mobilePhone = in.readString();
        this.officePhone = in.readString();
        this.description = in.readString();
        this.updateDate = in.readString();
        this.updator = in.readLong();
        this.countryRegion = in.readString();
        this.industry = in.readInt();
        this.domainID = in.readString();
        this.domainPath = in.readString();
        this.address = in.readString();
        this.city = in.readString();
        this.province = in.readString();
        this.website = in.readString();
        this.business = in.readString();
        this.isDemo = in.readInt();
        this.domains = in.readString();
        this.subDomain = in.readString();
        this.enterpriseDomain = in.readString();
        this.enterpriseName = in.readString();
        this.userDomainPathList = in.createStringArrayList();
        this.functionCodeSet = in.createStringArrayList();
        this.enterprise = in.readParcelable(EnterpriseBean.class.getClassLoader());
        this.userDomainList = in.createTypedArrayList(UserDomainBean.CREATOR);
    }

    public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel source) {
            return new UserBean(source);
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };
}
