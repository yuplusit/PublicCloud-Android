package com.jcodecraeer.xrecyclerview;

/**
 * Created by jianghejie on 15/11/22.
 */
public interface BaseRefreshHeader {

    int STATE_NORMAL             = 0;
    int STATE_RELEASE_TO_REFRESH = 1;
    int STATE_REFRESHING         = 2;
    int STATE_DONE               = 3;

    int ZERO_DIRECTION = -1;
    int UP_DIRECTION   = 0;
    int DOWN_DIRECTION = 1;

    void onMove(float delta);

    boolean releaseAction();

    void refreshComplete();

}