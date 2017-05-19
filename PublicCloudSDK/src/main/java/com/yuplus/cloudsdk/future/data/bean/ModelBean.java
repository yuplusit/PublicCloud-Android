package com.yuplus.cloudsdk.future.data.bean;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseBean;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public class ModelBean extends BaseBean{

    private String domainPath;
    private long   id;
    private String label;
    private String createTime;
    private String modifyTime;

    private ValuesBean values;
    private long    parentModelId;
    private String  modelPath;
    private int     dashboardViewId;
    private int     industry;
    private int     baseModelId;
    private int     originalModelId;
    private String  category;
    private String  icon;
    private String  desc;
    private String  resTitleTemplate;
    private int     order;
    private boolean canEdit;
    private int     solutionId;

    public String getDomainPath() {
        return domainPath;
    }

    public void setDomainPath(String domainPath) {
        this.domainPath = domainPath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public ValuesBean getValues() {
        return values;
    }

    public void setValues(ValuesBean values) {
        this.values = values;
    }

    public long getParentModelId() {
        return parentModelId;
    }

    public void setParentModelId(long parentModelId) {
        this.parentModelId = parentModelId;
    }

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }

    public int getDashboardViewId() {
        return dashboardViewId;
    }

    public void setDashboardViewId(int dashboardViewId) {
        this.dashboardViewId = dashboardViewId;
    }

    public int getIndustry() {
        return industry;
    }

    public void setIndustry(int industry) {
        this.industry = industry;
    }

    public int getBaseModelId() {
        return baseModelId;
    }

    public void setBaseModelId(int baseModelId) {
        this.baseModelId = baseModelId;
    }

    public int getOriginalModelId() {
        return originalModelId;
    }

    public void setOriginalModelId(int originalModelId) {
        this.originalModelId = originalModelId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIcon() {
        return icon;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public int getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(int solutionId) {
        this.solutionId = solutionId;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getResTitleTemplate() {
        return resTitleTemplate;
    }

    public void setResTitleTemplate(String resTitleTemplate) {
        this.resTitleTemplate = resTitleTemplate;
    }

    public static class ValuesBean extends BaseBean{
        private String modelType;
        private String series;
        private String modelNo;

        public String getModelType() {
            return modelType;
        }

        public void setModelType(String modelType) {
            this.modelType = modelType;
        }

        public String getSeries() {
            return series;
        }

        public void setSeries(String series) {
            this.series = series;
        }

        public String getModelNo() {
            return modelNo;
        }

        public void setModelNo(String modelNo) {
            this.modelNo = modelNo;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.modelType);
            dest.writeString(this.series);
            dest.writeString(this.modelNo);
        }

        public ValuesBean() {
        }

        protected ValuesBean(Parcel in) {
            this.modelType = in.readString();
            this.series = in.readString();
            this.modelNo = in.readString();
        }

        public static final Creator<ValuesBean> CREATOR = new Creator<ValuesBean>() {
            @Override
            public ValuesBean createFromParcel(Parcel source) {
                return new ValuesBean(source);
            }

            @Override
            public ValuesBean[] newArray(int size) {
                return new ValuesBean[size];
            }
        };
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.domainPath);
        dest.writeLong(this.id);
        dest.writeString(this.label);
        dest.writeString(this.createTime);
        dest.writeString(this.modifyTime);
        dest.writeParcelable(this.values, flags);
        dest.writeLong(this.parentModelId);
        dest.writeString(this.modelPath);
        dest.writeInt(this.dashboardViewId);
        dest.writeInt(this.industry);
        dest.writeInt(this.baseModelId);
        dest.writeInt(this.originalModelId);
        dest.writeString(this.category);
        dest.writeString(this.icon);
        dest.writeString(this.desc);
        dest.writeString(this.resTitleTemplate);
        dest.writeInt(this.order);
        dest.writeByte(this.canEdit ? (byte) 1 : (byte) 0);
        dest.writeInt(this.solutionId);
    }

    public ModelBean() {
    }

    protected ModelBean(Parcel in) {
        this.domainPath = in.readString();
        this.id = in.readLong();
        this.label = in.readString();
        this.createTime = in.readString();
        this.modifyTime = in.readString();
        this.values = in.readParcelable(ValuesBean.class.getClassLoader());
        this.parentModelId = in.readLong();
        this.modelPath = in.readString();
        this.dashboardViewId = in.readInt();
        this.industry = in.readInt();
        this.baseModelId = in.readInt();
        this.originalModelId = in.readInt();
        this.category = in.readString();
        this.icon = in.readString();
        this.desc = in.readString();
        this.resTitleTemplate = in.readString();
        this.order = in.readInt();
        this.canEdit = in.readByte() != 0;
        this.solutionId = in.readInt();
    }

    public static final Creator<ModelBean> CREATOR = new Creator<ModelBean>() {
        @Override
        public ModelBean createFromParcel(Parcel source) {
            return new ModelBean(source);
        }

        @Override
        public ModelBean[] newArray(int size) {
            return new ModelBean[size];
        }
    };
}
