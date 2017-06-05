package com.yuplus.cloudsdk.future.data.bean;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @user longzhen
 * @date 5/19/2017
 * @desc
 */

public class TicketBean extends BaseBean {

    private String ticketNo;
    private int    priorityCode;
    private int    status;
    private String category;
    private long   ticketCategoryId;
    private long    processDefinitionId;
    private String title;
    private String message;
    private long   creatorId;
    private String creatorName;
    private long    handlerId;
    private String handlerName;
    private String candidateUserOrGroup;
    private String finishedTime;
    private String commitTime;
    private String cancelTime;

    private ValuesBean values;
    private String     processInstanceId;
    private String     domainPath;
    private long       deviceId;
    private long        faultId;
    private String     origType;
    private long        maintenanceTaskId;
    private String     projectDomains;
    private boolean    newDesign;
    private List<Long> origId;

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public int getPriorityCode() {
        return priorityCode;
    }

    public void setPriorityCode(int priorityCode) {
        this.priorityCode = priorityCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getTicketCategoryId() {
        return ticketCategoryId;
    }

    public void setTicketCategoryId(long ticketCategoryId) {
        this.ticketCategoryId = ticketCategoryId;
    }

    public long getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(long processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public long getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(long handlerId) {
        this.handlerId = handlerId;
    }

    public Object getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public String getCandidateUserOrGroup() {
        return candidateUserOrGroup;
    }

    public void setCandidateUserOrGroup(String candidateUserOrGroup) {
        this.candidateUserOrGroup = candidateUserOrGroup;
    }

    public Object getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(String finishedTime) {
        this.finishedTime = finishedTime;
    }

    public String getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(String commitTime) {
        this.commitTime = commitTime;
    }

    public Object getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }

    public ValuesBean getValues() {
        return values;
    }

    public void setValues(ValuesBean values) {
        this.values = values;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getDomainPath() {
        return domainPath;
    }

    public void setDomainPath(String domainPath) {
        this.domainPath = domainPath;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public long getFaultId() {
        return faultId;
    }

    public void setFaultId(long faultId) {
        this.faultId = faultId;
    }

    public String getOrigType() {
        return origType;
    }

    public void setOrigType(String origType) {
        this.origType = origType;
    }

    public long getMaintenanceTaskId() {
        return maintenanceTaskId;
    }

    public void setMaintenanceTaskId(long maintenanceTaskId) {
        this.maintenanceTaskId = maintenanceTaskId;
    }

    public Object getProjectDomains() {
        return projectDomains;
    }

    public void setProjectDomains(String projectDomains) {
        this.projectDomains = projectDomains;
    }

    public boolean isNewDesign() {
        return newDesign;
    }

    public void setNewDesign(boolean newDesign) {
        this.newDesign = newDesign;
    }

    public List<Long> getOrigId() {
        return origId;
    }

    public void setOrigId(List<Long> origId) {
        this.origId = origId;
    }

    public static class ValuesBean extends BaseBean{
        private String ticketNo;
        private int    faultId;
        private long   deviceId;

        public String getTicketNo() {
            return ticketNo;
        }

        public void setTicketNo(String ticketNo) {
            this.ticketNo = ticketNo;
        }

        public int getFaultId() {
            return faultId;
        }

        public void setFaultId(int faultId) {
            this.faultId = faultId;
        }

        public long getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(long deviceId) {
            this.deviceId = deviceId;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.ticketNo);
            dest.writeInt(this.faultId);
            dest.writeLong(this.deviceId);
        }

        public ValuesBean() {
        }

        protected ValuesBean(Parcel in) {
            this.ticketNo = in.readString();
            this.faultId = in.readInt();
            this.deviceId = in.readLong();
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
        dest.writeString(this.ticketNo);
        dest.writeInt(this.priorityCode);
        dest.writeInt(this.status);
        dest.writeString(this.category);
        dest.writeLong(this.ticketCategoryId);
        dest.writeLong(this.processDefinitionId);
        dest.writeString(this.title);
        dest.writeString(this.message);
        dest.writeLong(this.creatorId);
        dest.writeString(this.creatorName);
        dest.writeLong(this.handlerId);
        dest.writeString(this.handlerName);
        dest.writeString(this.candidateUserOrGroup);
        dest.writeString(this.finishedTime);
        dest.writeString(this.commitTime);
        dest.writeString(this.cancelTime);
        dest.writeParcelable(this.values, flags);
        dest.writeString(this.processInstanceId);
        dest.writeString(this.domainPath);
        dest.writeLong(this.deviceId);
        dest.writeLong(this.faultId);
        dest.writeString(this.origType);
        dest.writeLong(this.maintenanceTaskId);
        dest.writeString(this.projectDomains);
        dest.writeByte(this.newDesign ? (byte) 1 : (byte) 0);
        dest.writeList(this.origId);
    }

    public TicketBean() {
    }

    protected TicketBean(Parcel in) {
        this.ticketNo = in.readString();
        this.priorityCode = in.readInt();
        this.status = in.readInt();
        this.category = in.readString();
        this.ticketCategoryId = in.readLong();
        this.processDefinitionId = in.readLong();
        this.title = in.readString();
        this.message = in.readString();
        this.creatorId = in.readLong();
        this.creatorName = in.readString();
        this.handlerId = in.readLong();
        this.handlerName = in.readString();
        this.candidateUserOrGroup = in.readString();
        this.finishedTime = in.readString();
        this.commitTime = in.readString();
        this.cancelTime = in.readString();
        this.values = in.readParcelable(ValuesBean.class.getClassLoader());
        this.processInstanceId = in.readString();
        this.domainPath = in.readString();
        this.deviceId = in.readLong();
        this.faultId = in.readLong();
        this.origType = in.readString();
        this.maintenanceTaskId = in.readLong();
        this.projectDomains = in.readString();
        this.newDesign = in.readByte() != 0;
        this.origId = new ArrayList<Long>();
        in.readList(this.origId, Long.class.getClassLoader());
    }

    public static final Creator<TicketBean> CREATOR = new Creator<TicketBean>() {
        @Override
        public TicketBean createFromParcel(Parcel source) {
            return new TicketBean(source);
        }

        @Override
        public TicketBean[] newArray(int size) {
            return new TicketBean[size];
        }
    };
}
