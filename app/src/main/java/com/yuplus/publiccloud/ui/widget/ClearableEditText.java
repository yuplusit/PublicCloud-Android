package com.yuplus.publiccloud.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.yuplus.cloudsdk.util.StringUtils;

/**
 * @user longzhen
 * @date 5/18/2017
 * @desc
 */
public class ClearableEditText extends EditText implements View.OnTouchListener,
        View.OnFocusChangeListener, TextWatcherAdapter.TextWatcherListener {

    public ClearableEditText(Context context) {
        super(context);
        init();
    }


    public ClearableEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public ClearableEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mDrawable = getCompoundDrawables()[2];
        if (mDrawable == null) {
            mDrawable = getResources()
                    .getDrawable(android.R.drawable.presence_offline);
        }
        mDrawable.setBounds(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());
        setClearIconVisible(false);
        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        addTextChangedListener(new TextWatcherAdapter(this, this));
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            setClearIconVisible(StringUtils.isNotEmpty(getText().toString()));
        } else {
            setClearIconVisible(false);
        }
        if (mOnFocusChangeListener != null) {
            mOnFocusChangeListener.onFocusChange(v, hasFocus);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (getCompoundDrawables()[2] != null) {
            boolean tappedX = event.getX() > (getWidth() - getPaddingRight() - mDrawable
                    .getIntrinsicWidth());
            if (tappedX) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    setText("");
                    if (mListener != null) {
                        mListener.didClearText();
                    }
                }
                return true;
            }
        }
        if (mOnTouchListener != null) {
            return mOnTouchListener.onTouch(v, event);
        }
        return false;
    }

    protected void setClearIconVisible(boolean visible) {
        boolean wasVisible = (getCompoundDrawables()[2] != null);
        if (visible != wasVisible) {
            Drawable x = visible ? mDrawable : null;
            setCompoundDrawables(getCompoundDrawables()[0],
                    getCompoundDrawables()[1], x, getCompoundDrawables()[3]);
        }
    }

    @Override
    public void onTextChanged(EditText view, String text) {
        if (isFocused()) {
            setClearIconVisible(StringUtils.isNotEmpty(text));
        }
    }

    public interface Listener {
        void didClearText();
    }

    public void setListener(Listener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        this.mOnTouchListener = l;
    }


    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener f) {
        this.mOnFocusChangeListener = f;
    }

    private Drawable              mDrawable;
    private Listener              mListener;
    private OnTouchListener       mOnTouchListener;
    private OnFocusChangeListener mOnFocusChangeListener;
}
