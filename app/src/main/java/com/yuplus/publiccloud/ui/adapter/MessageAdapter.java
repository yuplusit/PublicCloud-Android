package com.yuplus.publiccloud.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lb.materialdesigndialog.base.DialogBase;
import com.lb.materialdesigndialog.base.DialogWithTitle;
import com.lb.materialdesigndialog.impl.MaterialDialogNormal;
import com.yuplus.cloudsdk.future.data.bean.MessageBean;
import com.yuplus.cloudsdk.future.data.bean.MessageWrapperBean;
import com.yuplus.cloudsdk.util.StringUtils;
import com.yuplus.publiccloud.R;
import com.yuplus.publiccloud.enums.EMessageType;
import com.yuplus.publiccloud.util.DateUtils;
import com.yuplus.publiccloud.util.DensityUtils;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zlzsa on 2017/6/4.
 */

public class MessageAdapter extends BaseUltimateViewAdapter<MessageWrapperBean> {
    private OnBroadCastListener mOnBroadCastListener;

    public MessageAdapter(Context context, List<MessageWrapperBean> data) {
        super(context, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (position < getItemCount()) {
            final ViewHolder viewHolder = (ViewHolder) holder;
            final MessageWrapperBean messageWrapperBean = getItem(position);
            final MessageBean message = messageWrapperBean.getMessage();
            if (null == message) {
                return;
            }
            if (StringUtils.isNotBlank(message.getTitle())) {
                viewHolder.mTitleTv.setText(message.getTitle());
            }
            if (StringUtils.isNotBlank(message.getInsertTime())) {
                viewHolder.mDateTv.setText(DateUtils.timeFormat(message.getInsertTime(), "yyyy-MM-dd hh:mm"));
            }
            if (StringUtils.isNotBlank(message.getMsgType())) {
                EMessageType messageType = getMessageTypeValue(message.getMsgType());
                viewHolder.mTypeTv.setText(String.format("消息类型：%1s", messageType.getValue()));
                viewHolder.mMsgFlagTv.setText(messageType.getBrief());
                viewHolder.mMsgFlagTv.setBackground(createRoundCornerShapeDrawable(DensityUtils.dip2px(mContext,15),
                        Color.parseColor(messageType.getColor())));
            }
            viewHolder.mMsgLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.mMsgLl.setSelected(true);
                    MaterialDialogNormal dialog = new MaterialDialogNormal(mContext);
                    dialog.setTitle(message.getTitle());
                    dialog.setMessage(message.getContent());
                    dialog.setPositiveButton("确定", new DialogWithTitle.OnClickListener() {
                        @Override
                        public void click(DialogBase dialog, View view) {
                            dialog.dismiss();
                        }
                    });
                    if (null != mOnBroadCastListener) {
                        mOnBroadCastListener.OnBroadCast(message.getMessageId());
                    }
                }
            });
        }
    }

    private EMessageType getMessageTypeValue(String type) {
        if (EMessageType.TYPE_TICKET_MESSAGE.getType().equalsIgnoreCase(type)) {
            return EMessageType.TYPE_TICKET_MESSAGE;
        } else if (EMessageType.TYPE_MAINTENANCE_MESSAGE.getType().equalsIgnoreCase(type)) {
            return EMessageType.TYPE_MAINTENANCE_MESSAGE;
        } else if (EMessageType.TYPE_NOTIFY_MESSAGE.getType().equalsIgnoreCase(type)) {
            return EMessageType.TYPE_NOTIFY_MESSAGE;
        } else if (EMessageType.TYPE_TICKET_SERVICE_MESSAGE.getType().equalsIgnoreCase(type)) {
            return EMessageType.TYPE_TICKET_SERVICE_MESSAGE;
        } else if (EMessageType.TYPE_BULLETIN_MESSAGE.getType().equalsIgnoreCase(type)) {
            return EMessageType.TYPE_BULLETIN_MESSAGE;
        } else if (EMessageType.TYPE_PAYMENT_MESSAGE.getType().equalsIgnoreCase(type)) {
            return EMessageType.TYPE_PAYMENT_MESSAGE;
        } else if (EMessageType.TYPE_MAINTENANCE_MSG.getType().equalsIgnoreCase(type)) {
            return EMessageType.TYPE_MAINTENANCE_MSG;
        } else if (EMessageType.TYPE_ALERT_MESSAGE_INSYSTEM.getType().equalsIgnoreCase(type)) {
            return EMessageType.TYPE_ALERT_MESSAGE_INSYSTEM;
        } else if (EMessageType.TYPE_GATEWAY_MESSAGE.getType().equalsIgnoreCase(type)) {
            return EMessageType.TYPE_GATEWAY_MESSAGE;
        } else if (EMessageType.TYPE_COMMAND_MESSAGE.getType().equalsIgnoreCase(type)) {
            return EMessageType.TYPE_COMMAND_MESSAGE;
        } else if (EMessageType.TYPE_ENERGYCONSUME_MESSAGE.getType().equalsIgnoreCase(type)) {
            return EMessageType.TYPE_ENERGYCONSUME_MESSAGE;
        } else if (EMessageType.TYPE_DATAREPORT_MESSAGE.getType().equalsIgnoreCase(type)) {
            return EMessageType.TYPE_DATAREPORT_MESSAGE;
        } else {
            return EMessageType.TYPE_TICKET_MESSAGE;
        }
    }

    public static ShapeDrawable createRoundCornerShapeDrawable(float radius, int innerColor) {
        float[] outerRadii = new float[8];
        float[] innerRadii = new float[8];
        for (int i = 0; i < 8; i++) {
            outerRadii[i] = radius;
            innerRadii[i] = radius;
        }

        ShapeDrawable sd = new ShapeDrawable(new RoundRectShape(outerRadii, new RectF(0, 0,
                0, 0), innerRadii));
        sd.getPaint().setColor(innerColor);

        return sd;
    }

    public void setOnBroadCastListener(OnBroadCastListener listener) {
        this.mOnBroadCastListener = listener;
    }

    public interface OnBroadCastListener {
        void OnBroadCast(long msgId);
    }

    class ViewHolder extends UltimateViewHolder {
        @BindView(R.id.message_id_flag)
        TextView     mMsgFlagTv;
        @BindView(R.id.message_id_item_layout)
        LinearLayout mMsgLl;
        @BindView(R.id.message_id_desc)
        TextView     mTitleTv;
        @BindView(R.id.message_id_date)
        TextView     mDateTv;
        @BindView(R.id.message_id_type)
        TextView     mTypeTv;

        public ViewHolder(View itemView) {
            super(itemView, onItemClickListener);
        }
    }
}
