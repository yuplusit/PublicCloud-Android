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
}
