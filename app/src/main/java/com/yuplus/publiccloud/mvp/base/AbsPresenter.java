package com.yuplus.publiccloud.mvp.base;

/**
 * @user longzhen
 * @date 5/18/2017
 * @desc
 */

public abstract class AbsPresenter<T extends IView> {
    private   T      view;
    protected Object tag; //此tag可指Activity、Fragment、Presenter

    public AbsPresenter() {
        init();
    }

    public T getView() {
        return view;
    }

    public AbsPresenter setView(T view) {
        this.view = view;
        return this;
    }

    public Object getTag() {
        return tag;
    }

    public AbsPresenter setTag(Object tag) {
        this.tag = tag;
        return this;
    }

    public abstract void init();

    public abstract void resume();

    public abstract void pause();

    public abstract void destroy();

}
