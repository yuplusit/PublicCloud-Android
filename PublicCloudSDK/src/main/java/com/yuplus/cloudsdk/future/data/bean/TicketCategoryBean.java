package com.yuplus.cloudsdk.future.data.bean;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @user longzhen
 * @date 6/13/2017
 * @desc
 */

public class TicketCategoryBean extends BaseBean {

    private String                domainPath;
    private long                  id;
    private String                label;
    private String                createTime;
    private String                modifyTime;
    private long                  workflowId;
    private String                name;
    private String                category;
    private String                desc;
    private List<HanderConfsBean> handerConfs;

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

    public long getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(long workflowId) {
        this.workflowId = workflowId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<HanderConfsBean> getHanderConfs() {
        return handerConfs;
    }

    public void setHanderConfs(List<HanderConfsBean> handerConfs) {
        this.handerConfs = handerConfs;
    }

    public static class HanderConfsBean extends BaseBean{

        private String       userType;
        private List<Long>   userIds;
        private List<Long>   userGroupIds;
        private List<String> userExpression;
        private List<String> userIdsOfRole;
        private List<String> userExpressionOfCategory;
        private String       taskName;
        private String       taskId;
        private List<Long>   roleIds;

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public List<Long> getUserIds() {
            return userIds;
        }

        public void setUserIds(List<Long> userIds) {
            this.userIds = userIds;
        }

        public List<Long> getUserGroupIds() {
            return userGroupIds;
        }

        public void setUserGroupIds(List<Long> userGroupIds) {
            this.userGroupIds = userGroupIds;
        }

        public List<String> getUserExpression() {
            return userExpression;
        }

        public void setUserExpression(List<String> userExpression) {
            this.userExpression = userExpression;
        }

        public List<String> getUserIdsOfRole() {
            return userIdsOfRole;
        }

        public void setUserIdsOfRole(List<String> userIdsOfRole) {
            this.userIdsOfRole = userIdsOfRole;
        }

        public List<String> getUserExpressionOfCategory() {
            return userExpressionOfCategory;
        }

        public void setUserExpressionOfCategory(List<String> userExpressionOfCategory) {
            this.userExpressionOfCategory = userExpressionOfCategory;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

        public List<Long> getRoleIds() {
            return roleIds;
        }

        public void setRoleIds(List<Long> roleIds) {
            this.roleIds = roleIds;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.userType);
            dest.writeList(this.userIds);
            dest.writeList(this.userGroupIds);
            dest.writeStringList(this.userExpression);
            dest.writeStringList(this.userIdsOfRole);
            dest.writeStringList(this.userExpressionOfCategory);
            dest.writeString(this.taskName);
            dest.writeString(this.taskId);
            dest.writeList(this.roleIds);
        }

        public HanderConfsBean() {
        }

        protected HanderConfsBean(Parcel in) {
            this.userType = in.readString();
            this.userIds = new ArrayList<Long>();
            in.readList(this.userIds, Long.class.getClassLoader());
            this.userGroupIds = new ArrayList<Long>();
            in.readList(this.userGroupIds, Long.class.getClassLoader());
            this.userExpression = in.createStringArrayList();
            this.userIdsOfRole = in.createStringArrayList();
            this.userExpressionOfCategory = in.createStringArrayList();
            this.taskName = in.readString();
            this.taskId = in.readString();
            this.roleIds = new ArrayList<Long>();
            in.readList(this.roleIds, Long.class.getClassLoader());
        }

        public static final Creator<HanderConfsBean> CREATOR = new Creator<HanderConfsBean>() {
            @Override
            public HanderConfsBean createFromParcel(Parcel source) {
                return new HanderConfsBean(source);
            }

            @Override
            public HanderConfsBean[] newArray(int size) {
                return new HanderConfsBean[size];
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
        dest.writeLong(this.workflowId);
        dest.writeString(this.name);
        dest.writeString(this.category);
        dest.writeString(this.desc);
        dest.writeTypedList(this.handerConfs);
    }

    public TicketCategoryBean() {
    }

    protected TicketCategoryBean(Parcel in) {
        this.domainPath = in.readString();
        this.id = in.readLong();
        this.label = in.readString();
        this.createTime = in.readString();
        this.modifyTime = in.readString();
        this.workflowId = in.readLong();
        this.name = in.readString();
        this.category = in.readString();
        this.desc = in.readString();
        this.handerConfs = in.createTypedArrayList(HanderConfsBean.CREATOR);
    }

    public static final Creator<TicketCategoryBean> CREATOR = new Creator<TicketCategoryBean>() {
        @Override
        public TicketCategoryBean createFromParcel(Parcel source) {
            return new TicketCategoryBean(source);
        }

        @Override
        public TicketCategoryBean[] newArray(int size) {
            return new TicketCategoryBean[size];
        }
    };
}
