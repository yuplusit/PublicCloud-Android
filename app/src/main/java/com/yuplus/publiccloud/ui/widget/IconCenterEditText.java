package com.yuplus.publiccloud.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.yuplus.cloudsdk.util.StringUtils;

/**
 * @user longzhen
 * @date 5/18/2017
 * @desc
 */
public class IconCenterEditText extends EditText implements View.OnTouchListener,
        View.OnFocusChangeListener, TextWatcherAdapter.TextWatcherListener, View.OnKeyListener {

    private static final String TAG = IconCenterEditText.class.getSimpleName();

    private boolean isLeft      = false;
    private boolean pressSearch = false;

    private Drawable              mDrawable;
    private Listener              mListener;
    private OnTouchListener       mOnTouchListener;
    private OnFocusChangeListener mOnFocusChangeListener;
    private OnSearchClickListener listener;

    public void setOnSearchClickListener(OnSearchClickListener listener) {
        this.listener = listener;
    }

    public IconCenterEditText(Context context) {
        this(context, null);
        init();
    }

    public IconCenterEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
        init();
    }

    public IconCenterEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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

        setOnFocusChangeListener(this);
        setOnKeyListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isLeft) { // 如果是默认样式，则直接绘制
            super.onDraw(canvas);
        } else { // 如果不是默认样式，需要将图标绘制在中间
            Drawable[] drawables = getCompoundDrawables();
            Drawable drawableLeft = drawables[0];
            Drawable drawableRight = drawables[2];
            translate(drawableLeft, canvas);
            translate(drawableRight, canvas);
            super.onDraw(canvas);
        }

    }

    public void translate(Drawable drawable, Canvas canvas) {
        if (drawable != null) {
            float textWidth = getPaint().measureText(getHint().toString());
            int drawablePadding = getCompoundDrawablePadding();
            int drawableWidth = drawable.getIntrinsicWidth();
            float bodyWidth = textWidth + drawableWidth + drawablePadding;
            if (drawable == getCompoundDrawables()[0]) {
                canvas.translate((getWidth() - bodyWidth - getPaddingLeft() - getPaddingRight()) / 2, 0);
            } else {
                setPadding(getPaddingLeft(), getPaddingTop(), (int) (getWidth() - bodyWidth - getPaddingLeft()), getPaddingBottom());
                canvas.translate((getWidth() - bodyWidth - getPaddingLeft()) / 2, 0);
            }
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        Log.d(TAG, "onFocusChange execute");
        if (hasFocus) {
            setClearIconVisible(StringUtils.isNotEmpty(getText().toString()));
        } else {
            setClearIconVisible(false);
        }
        // 恢复EditText默认的样式
        if (!pressSearch && TextUtils.isEmpty(getText().toString())) {
            isLeft = hasFocus;
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        pressSearch = (keyCode == KeyEvent.KEYCODE_ENTER);
        if (pressSearch && listener != null) {
            /*隐藏软键盘*/
            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
            }
            if (event.getAction() == KeyEvent.ACTION_UP) {
                listener.onSearchClick(v);
            }
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

    public interface OnSearchClickListener {
        void onSearchClick(View view);
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
}