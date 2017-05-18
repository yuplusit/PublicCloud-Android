package com.yuplus.publiccloud.ui.widget;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * @user longzhen
 * @date 5/18/2017
 * @desc
 */
public class TextWatcherAdapter implements TextWatcher {
    public TextWatcherAdapter(EditText editText, TextWatcherListener listener) {
        this.mView = editText;
        this.mListener = listener;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mListener.onTextChanged(mView, s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public interface TextWatcherListener {
        void onTextChanged(EditText view, String text);
    }

    private final EditText            mView;
    private final TextWatcherListener mListener;
}
