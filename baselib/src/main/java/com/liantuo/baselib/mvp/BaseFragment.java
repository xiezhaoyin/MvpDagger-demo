package com.liantuo.baselib.mvp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;

import java.io.Serializable;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;


/**
 * Created by dell on 2018/3/16.
 */

public abstract class BaseFragment<T extends BasePresenter> extends RxFragment implements IAttachEvent, ILifecycleView, OnReceiveListener {

    protected String TAG = null;
    private int layoutResId = 0;
    private EventDispatch dispatch = null;
    private Unbinder unbinder = null;
    private boolean isCreated = false;
    private boolean isFirstCreated = true;
    protected int index = -1;
    protected Serializable args = null;
    private View parentView = null;

    @Inject
    protected T presenter;

    // 接收参数
    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        Bundle arguments = getArguments();
        if (arguments != null) {
            index = arguments.getInt("index", -1);
            args = arguments.getSerializable("args");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        TAG = getClass().getSimpleName();
        Log.d(TAG, index + " onCreateView");

        layoutResId = this.createView();
        if (layoutResId != 0) {
            if (null == parentView)
                parentView = inflater.inflate(layoutResId, null);
            unbinder = ButterKnife.bind(this, parentView);
            isFirstCreated = false;
            isCreated = true;

            if (presenter != null)
                presenter.createPresenter((IBaseView) this);

            this.initView(savedInstanceState);

            if (dispatch == null) {
                dispatch = attachEvent(new EventDispatch(), this);
            }
            return parentView;
        } else {
            throw new NullPointerException("createView don't be null");
        }

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public EventDispatch attachEvent(EventDispatch dispatch, OnReceiveListener listener) {
        dispatch.register(listener);
        return dispatch;
    }

    @Override
    public void detachEvent() {
        if (dispatch != null) {
            dispatch.unregister();
            dispatch = null;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, index + " setUserVisibleHint : isVisibleToUser == " + isVisibleToUser);
        if (isVisibleToUser) {
            if (isCreated) {
                Log.d(TAG, index + " resumeView");
                this.resumeView();
                if (isFirstCreated)
                    this.lazyLoad();
            }
        }
    }

    protected void lazyLoad() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, index + " onDestroyView");
        this.destroyView();
        isFirstCreated = true;
        isCreated = false;
        if (presenter != null)
            presenter.destroyPresenter();
        unbinder.unbind();
        detachEvent();
    }

    protected void gotoActivity(Class cl, @Nullable Object object) {
        gotoActivity(cl, object, true);
    }

    protected void gotoActivity(Class cl, @Nullable Object object, boolean isSticky) {
        Intent intent = new Intent(getActivity(), cl);
        if (object != null && dispatch != null) {
            dispatch.post(object, isSticky);
        }
        startActivity(intent);
    }

    // 携带参数
    public BaseFragment newInstance(BaseFragment fragment, int index, Serializable args) {
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        bundle.putSerializable("args", args);
        fragment.setArguments(bundle);
        return fragment;
    }

    // 携带参数
    public BaseFragment newInstance(BaseFragment fragment, int index) {
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        fragment.setArguments(bundle);
        return fragment;
    }

    // 更新参数
    public void updateArguments(int index) {
        this.index = index;
        Bundle args = getArguments();
        if (args != null) {
            args.putInt("index", index);
        }
    }

}
