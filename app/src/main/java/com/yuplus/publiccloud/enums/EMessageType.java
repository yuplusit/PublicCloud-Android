package com.yuplus.publiccloud.enums;

/**
 * @user longzhen
 * @date 6/14/2017
 * @desc
 */

public enum EMessageType {

    TYPE_TICKET_MESSAGE("ticket_message", "工单任务", "#2b91e7", "工"),
    TYPE_MAINTENANCE_MESSAGE("maintenance_message", "维保", "#00b9b2", "维"),
    TYPE_NOTIFY_MESSAGE("notify_message", "消息通知", "#81c75b", "消"),
    TYPE_TICKET_SERVICE_MESSAGE("ticket_service_message", "服务任务", "#899fb1", "任"),
    TYPE_BULLETIN_MESSAGE("bulletin_message", "系统公告", "#7a83de", "公"),
    TYPE_PAYMENT_MESSAGE("payment_message", "缴费消息", "#e66b61", "缴"),
    TYPE_MAINTENANCE_MSG("maintenance_msg", "维保计划", "#c496c2", "计"),
    TYPE_ALERT_MESSAGE_INSYSTEM("alert_message_insystem", "设备告警", "#e6a313", "告"),
    TYPE_GATEWAY_MESSAGE("gateway_message", "网关消息", "#af652f", "关"),
    TYPE_COMMAND_MESSAGE("command_message", "指令消息", "#af652f", "令"),
    TYPE_ENERGYCONSUME_MESSAGE("energyconsume_message", "能耗结构消息", "#af652f", "耗"),
    TYPE_DATAREPORT_MESSAGE("datareport_message", "企业数据上报消息", "#af652f", "报");

    private String type;
    private String value;
    private String color;
    private String brief;


    EMessageType(String type, String value, String color, String brief) {
        this.type = type;
        this.value = value;
        this.color = color;
        this.brief = brief;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
}
