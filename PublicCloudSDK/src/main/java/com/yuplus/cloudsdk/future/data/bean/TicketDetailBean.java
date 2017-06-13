package com.yuplus.cloudsdk.future.data.bean;

import android.os.Parcel;

import com.yuplus.cloudsdk.base.BaseBean;

/**
 * @user longzhen
 * @date 6/12/2017
 * @desc
 */

public class TicketDetailBean extends BaseBean {

    private long id;
    private String         ticketNo;
    private String         message;
    private String         executeTime;
    private String         logType;
    private TicketTaskBean ticketTask;
    private long         workflowNodeId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public TicketTaskBean getTicketTask() {
        return ticketTask;
    }

    public void setTicketTask(TicketTaskBean ticketTask) {
        this.ticketTask = ticketTask;
    }

    public long getWorkflowNodeId() {
        return workflowNodeId;
    }

    public void setWorkflowNodeId(long workflowNodeId) {
        this.workflowNodeId = workflowNodeId;
    }

    public static class TicketTaskBean extends BaseBean{

        private long id;
        private String        desc;
        private long          handlerId;
        private String        handlerName;
        private int           taskStatus;
        private int           ticketPriorityCode;
        private String        taskConfigName;
        private String        ticketNo;
        private String        ticketTitle;
        private String        processTaskId;
        private long          processDefinitionId;
        private long          senderId;
        private String        senderName;
        private String        sendTime;
        private String        finishedTime;
        private int           taskStep;
        private VariablesBean variables;
        private String        taskDefinitionId;
        private String        templateURL;
        private int           stockOrderId;
        private long          deviceId;
        private long          maintenanceTaskId;
        private String        maintenanceContent;
        private String        images;
        private String        domainPath;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public long getHandlerId() {
            return handlerId;
        }

        public void setHandlerId(long handlerId) {
            this.handlerId = handlerId;
        }

        public String getHandlerName() {
            return handlerName;
        }

        public void setHandlerName(String handlerName) {
            this.handlerName = handlerName;
        }

        public int getTaskStatus() {
            return taskStatus;
        }

        public void setTaskStatus(int taskStatus) {
            this.taskStatus = taskStatus;
        }

        public int getTicketPriorityCode() {
            return ticketPriorityCode;
        }

        public void setTicketPriorityCode(int ticketPriorityCode) {
            this.ticketPriorityCode = ticketPriorityCode;
        }

        public String getTaskConfigName() {
            return taskConfigName;
        }

        public void setTaskConfigName(String taskConfigName) {
            this.taskConfigName = taskConfigName;
        }

        public String getTicketNo() {
            return ticketNo;
        }

        public void setTicketNo(String ticketNo) {
            this.ticketNo = ticketNo;
        }

        public String getTicketTitle() {
            return ticketTitle;
        }

        public void setTicketTitle(String ticketTitle) {
            this.ticketTitle = ticketTitle;
        }

        public String getProcessTaskId() {
            return processTaskId;
        }

        public void setProcessTaskId(String processTaskId) {
            this.processTaskId = processTaskId;
        }

        public long getProcessDefinitionId() {
            return processDefinitionId;
        }

        public void setProcessDefinitionId(long processDefinitionId) {
            this.processDefinitionId = processDefinitionId;
        }

        public long getSenderId() {
            return senderId;
        }

        public void setSenderId(long senderId) {
            this.senderId = senderId;
        }

        public String getSenderName() {
            return senderName;
        }

        public void setSenderName(String senderName) {
            this.senderName = senderName;
        }

        public String getSendTime() {
            return sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }

        public String getFinishedTime() {
            return finishedTime;
        }

        public void setFinishedTime(String finishedTime) {
            this.finishedTime = finishedTime;
        }

        public int getTaskStep() {
            return taskStep;
        }

        public void setTaskStep(int taskStep) {
            this.taskStep = taskStep;
        }

        public VariablesBean getVariables() {
            return variables;
        }

        public void setVariables(VariablesBean variables) {
            this.variables = variables;
        }

        public String getTaskDefinitionId() {
            return taskDefinitionId;
        }

        public void setTaskDefinitionId(String taskDefinitionId) {
            this.taskDefinitionId = taskDefinitionId;
        }

        public String getTemplateURL() {
            return templateURL;
        }

        public void setTemplateURL(String templateURL) {
            this.templateURL = templateURL;
        }

        public int getStockOrderId() {
            return stockOrderId;
        }

        public void setStockOrderId(int stockOrderId) {
            this.stockOrderId = stockOrderId;
        }

        public long getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(long deviceId) {
            this.deviceId = deviceId;
        }

        public long getMaintenanceTaskId() {
            return maintenanceTaskId;
        }

        public void setMaintenanceTaskId(long maintenanceTaskId) {
            this.maintenanceTaskId = maintenanceTaskId;
        }

        public String getMaintenanceContent() {
            return maintenanceContent;
        }

        public void setMaintenanceContent(String maintenanceContent) {
            this.maintenanceContent = maintenanceContent;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getDomainPath() {
            return domainPath;
        }

        public void setDomainPath(String domainPath) {
            this.domainPath = domainPath;
        }
        public static class VariablesBean extends BaseBean{

            private String faultPhenomenon;
            private ProjectBean  project;
            private String       ticketCommitTime;
            private long         deviceId;
            private String       deviceSn;
            private String       result;
            private String       ticketNo;
            private boolean      isUnderWarranty;
            private long         projectID;
            private String       email;
            private long         taskHandlePerson;
            private TicketBean   ticket;
            private long         modelID;
            private String       faultNo;
            private long         ticketCreatorID;
            private String       fault;
            private String       customerName;
            private String       modelName;
            private String       phone;
            private int          faultId;
            private String       ticketCreatorName;
            private long         customerID;
            private String       sendPhone;
            private int          priorityCode;
            private String       projectName;
            private DeviceBean   device;
            private CustomerBean customer;

            public String getFaultPhenomenon() {
                return faultPhenomenon;
            }

            public void setFaultPhenomenon(String faultPhenomenon) {
                this.faultPhenomenon = faultPhenomenon;
            }

            public ProjectBean getProject() {
                return project;
            }

            public void setProject(ProjectBean project) {
                this.project = project;
            }

            public String getTicketCommitTime() {
                return ticketCommitTime;
            }

            public void setTicketCommitTime(String ticketCommitTime) {
                this.ticketCommitTime = ticketCommitTime;
            }

            public long getDeviceId() {
                return deviceId;
            }

            public void setDeviceId(long deviceId) {
                this.deviceId = deviceId;
            }

            public String getDeviceSn() {
                return deviceSn;
            }

            public void setDeviceSn(String deviceSn) {
                this.deviceSn = deviceSn;
            }

            public String getResult() {
                return result;
            }

            public void setResult(String result) {
                this.result = result;
            }

            public String getTicketNo() {
                return ticketNo;
            }

            public void setTicketNo(String ticketNo) {
                this.ticketNo = ticketNo;
            }

            public boolean isIsUnderWarranty() {
                return isUnderWarranty;
            }

            public void setIsUnderWarranty(boolean isUnderWarranty) {
                this.isUnderWarranty = isUnderWarranty;
            }

            public long getProjectID() {
                return projectID;
            }

            public void setProjectID(long projectID) {
                this.projectID = projectID;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public long getTaskHandlePerson() {
                return taskHandlePerson;
            }

            public void setTaskHandlePerson(long taskHandlePerson) {
                this.taskHandlePerson = taskHandlePerson;
            }

            public TicketBean getTicket() {
                return ticket;
            }

            public void setTicket(TicketBean ticket) {
                this.ticket = ticket;
            }

            public long getModelID() {
                return modelID;
            }

            public void setModelID(long modelID) {
                this.modelID = modelID;
            }

            public String getFaultNo() {
                return faultNo;
            }

            public void setFaultNo(String faultNo) {
                this.faultNo = faultNo;
            }

            public long getTicketCreatorID() {
                return ticketCreatorID;
            }

            public void setTicketCreatorID(long ticketCreatorID) {
                this.ticketCreatorID = ticketCreatorID;
            }

            public String getFault() {
                return fault;
            }

            public void setFault(String fault) {
                this.fault = fault;
            }

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public String getModelName() {
                return modelName;
            }

            public void setModelName(String modelName) {
                this.modelName = modelName;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getFaultId() {
                return faultId;
            }

            public void setFaultId(int faultId) {
                this.faultId = faultId;
            }

            public String getTicketCreatorName() {
                return ticketCreatorName;
            }

            public void setTicketCreatorName(String ticketCreatorName) {
                this.ticketCreatorName = ticketCreatorName;
            }

            public long getCustomerID() {
                return customerID;
            }

            public void setCustomerID(long customerID) {
                this.customerID = customerID;
            }

            public String getSendPhone() {
                return sendPhone;
            }

            public void setSendPhone(String sendPhone) {
                this.sendPhone = sendPhone;
            }

            public int getPriorityCode() {
                return priorityCode;
            }

            public void setPriorityCode(int priorityCode) {
                this.priorityCode = priorityCode;
            }

            public String getProjectName() {
                return projectName;
            }

            public void setProjectName(String projectName) {
                this.projectName = projectName;
            }

            public DeviceBean getDevice() {
                return device;
            }

            public void setDevice(DeviceBean device) {
                this.device = device;
            }

            public CustomerBean getCustomer() {
                return customer;
            }

            public void setCustomer(CustomerBean customer) {
                this.customer = customer;
            }


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.faultPhenomenon);
                dest.writeParcelable(this.project, flags);
                dest.writeString(this.ticketCommitTime);
                dest.writeLong(this.deviceId);
                dest.writeString(this.deviceSn);
                dest.writeString(this.result);
                dest.writeString(this.ticketNo);
                dest.writeByte(this.isUnderWarranty ? (byte) 1 : (byte) 0);
                dest.writeLong(this.projectID);
                dest.writeString(this.email);
                dest.writeLong(this.taskHandlePerson);
                dest.writeParcelable(this.ticket, flags);
                dest.writeLong(this.modelID);
                dest.writeString(this.faultNo);
                dest.writeLong(this.ticketCreatorID);
                dest.writeString(this.fault);
                dest.writeString(this.customerName);
                dest.writeString(this.modelName);
                dest.writeString(this.phone);
                dest.writeInt(this.faultId);
                dest.writeString(this.ticketCreatorName);
                dest.writeLong(this.customerID);
                dest.writeString(this.sendPhone);
                dest.writeInt(this.priorityCode);
                dest.writeString(this.projectName);
                dest.writeParcelable(this.device, flags);
                dest.writeParcelable(this.customer, flags);
            }

            public VariablesBean() {
            }

            protected VariablesBean(Parcel in) {
                this.faultPhenomenon = in.readString();
                this.project = in.readParcelable(ProjectBean.class.getClassLoader());
                this.ticketCommitTime = in.readString();
                this.deviceId = in.readLong();
                this.deviceSn = in.readString();
                this.result = in.readString();
                this.ticketNo = in.readString();
                this.isUnderWarranty = in.readByte() != 0;
                this.projectID = in.readLong();
                this.email = in.readString();
                this.taskHandlePerson = in.readLong();
                this.ticket = in.readParcelable(TicketBean.class.getClassLoader());
                this.modelID = in.readLong();
                this.faultNo = in.readString();
                this.ticketCreatorID = in.readLong();
                this.fault = in.readString();
                this.customerName = in.readString();
                this.modelName = in.readString();
                this.phone = in.readString();
                this.faultId = in.readInt();
                this.ticketCreatorName = in.readString();
                this.customerID = in.readLong();
                this.sendPhone = in.readString();
                this.priorityCode = in.readInt();
                this.projectName = in.readString();
                this.device = in.readParcelable(DeviceBean.class.getClassLoader());
                this.customer = in.readParcelable(CustomerBean.class.getClassLoader());
            }

            public static final Creator<VariablesBean> CREATOR = new Creator<VariablesBean>() {
                @Override
                public VariablesBean createFromParcel(Parcel source) {
                    return new VariablesBean(source);
                }

                @Override
                public VariablesBean[] newArray(int size) {
                    return new VariablesBean[size];
                }
            };
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(this.id);
            dest.writeString(this.desc);
            dest.writeLong(this.handlerId);
            dest.writeString(this.handlerName);
            dest.writeInt(this.taskStatus);
            dest.writeInt(this.ticketPriorityCode);
            dest.writeString(this.taskConfigName);
            dest.writeString(this.ticketNo);
            dest.writeString(this.ticketTitle);
            dest.writeString(this.processTaskId);
            dest.writeLong(this.processDefinitionId);
            dest.writeLong(this.senderId);
            dest.writeString(this.senderName);
            dest.writeString(this.sendTime);
            dest.writeString(this.finishedTime);
            dest.writeInt(this.taskStep);
            dest.writeParcelable(this.variables, flags);
            dest.writeString(this.taskDefinitionId);
            dest.writeString(this.templateURL);
            dest.writeInt(this.stockOrderId);
            dest.writeLong(this.deviceId);
            dest.writeLong(this.maintenanceTaskId);
            dest.writeString(this.maintenanceContent);
            dest.writeString(this.images);
            dest.writeString(this.domainPath);
        }

        public TicketTaskBean() {
        }

        protected TicketTaskBean(Parcel in) {
            this.id = in.readLong();
            this.desc = in.readString();
            this.handlerId = in.readLong();
            this.handlerName = in.readString();
            this.taskStatus = in.readInt();
            this.ticketPriorityCode = in.readInt();
            this.taskConfigName = in.readString();
            this.ticketNo = in.readString();
            this.ticketTitle = in.readString();
            this.processTaskId = in.readString();
            this.processDefinitionId = in.readLong();
            this.senderId = in.readLong();
            this.senderName = in.readString();
            this.sendTime = in.readString();
            this.finishedTime = in.readString();
            this.taskStep = in.readInt();
            this.variables = in.readParcelable(VariablesBean.class.getClassLoader());
            this.taskDefinitionId = in.readString();
            this.templateURL = in.readString();
            this.stockOrderId = in.readInt();
            this.deviceId = in.readLong();
            this.maintenanceTaskId = in.readLong();
            this.maintenanceContent = in.readString();
            this.images = in.readString();
            this.domainPath = in.readString();
        }

        public static final Creator<TicketTaskBean> CREATOR = new Creator<TicketTaskBean>() {
            @Override
            public TicketTaskBean createFromParcel(Parcel source) {
                return new TicketTaskBean(source);
            }

            @Override
            public TicketTaskBean[] newArray(int size) {
                return new TicketTaskBean[size];
            }
        };
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.ticketNo);
        dest.writeString(this.message);
        dest.writeString(this.executeTime);
        dest.writeString(this.logType);
        dest.writeParcelable(this.ticketTask, flags);
        dest.writeLong(this.workflowNodeId);
    }

    public TicketDetailBean() {
    }

    protected TicketDetailBean(Parcel in) {
        this.id = in.readLong();
        this.ticketNo = in.readString();
        this.message = in.readString();
        this.executeTime = in.readString();
        this.logType = in.readString();
        this.ticketTask = in.readParcelable(TicketTaskBean.class.getClassLoader());
        this.workflowNodeId = in.readLong();
    }

    public static final Creator<TicketDetailBean> CREATOR = new Creator<TicketDetailBean>() {
        @Override
        public TicketDetailBean createFromParcel(Parcel source) {
            return new TicketDetailBean(source);
        }

        @Override
        public TicketDetailBean[] newArray(int size) {
            return new TicketDetailBean[size];
        }
    };
}
