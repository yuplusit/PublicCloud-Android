package com.yuplus.publiccloud.cst;

/**
 * @user longzhen
 * @date 6/1/2017
 * @desc
 */

public interface TypeCst {
    class CustomerArea {
        public final static int DEVICE_PAGE = 0;
        public final static int ALERT_PAGE  = 1;
        public final static int ORDER_PAGE  = 2;
    }

    class SlideArea {
        public final static int TYPE_MESSAGE       = 0;
        public final static int TYPE_SKIN_SELECT   = 1;
        public final static int TYPE_FEEDBACK_HELP = 2;
        public final static int TYPE_USER_INFO     = 3;
    }

    class TicketLogType {
        public final static String TYPE_START_EVENT = "startEvent";
        public final static String TYPE_USER_TASK   = "userTask";
        public final static String TYPE_END_EVENT   = "endEvent";
    }

    class MessageType {
        public final static String TYPE_TICKET_MESSAGE         = "ticket_message";//工单任务
        public final static String TYPE_MAINTENANCE_MESSAGE    = "maintenance_message";//维保
        public final static String TYPE_NOTIFY_MESSAGE         = "notify_message";//消息通知
        public final static String TYPE_TICKET_SERVICE_MESSAGE = "ticket_service_message";//服务任务
        public final static String TYPE_BULLETIN_MESSAGE       = "bulletin_message";//系统公告
        public final static String TYPE_PAYMENT_MESSAGE        = "payment_message";//缴费消息
        public final static String TYPE_MAINTENANCE_MSG        = "maintenance_msg";//维保计划
        public final static String TYPE_ALERT_MESSAGE_INSYSTEM = "alert_message_insystem";//设备告警
        public final static String TYPE_GATEWAY_MESSAGE        = "gateway_message";//网关消息
        public final static String TYPE_COMMAND_MESSAGE        = "command_message";//指令消息
        public final static String TYPE_ENERGYCONSUME_MESSAGE  = "energyconsume_message";//能耗结构消息
        public final static String TYPE_DATAREPORT_MESSAGE     = "datareport_message";//企业数据上报消息
    }
}
