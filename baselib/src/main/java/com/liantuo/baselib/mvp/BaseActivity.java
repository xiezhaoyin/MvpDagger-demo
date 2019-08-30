package com.liantuo.baselib.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.io.Serializable;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by dell on 2018/4/24.
 */
public abstract class BaseActivity<P extends IBasePresenter> extends RxAppCompatActivity implements IAttachEvent, ILifecycleView, OnReceiveListener, HasSupportFragmentInjector {

    protected String TAG = null;
    private int layoutResId = 0;
    protected EventDispatch dispatch = null;
    private Unbinder unbinder = null;
    private boolean isCreated = false;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Inject
    protected P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setStatusBar();
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();

        layoutResId = this.createView();
        if (layoutResId != 0) {
            setContentView(layoutResId);
            unbinder = ButterKnife.bind(this);
            isCreated = true;
        } else {
            throw new NullPointerException("createView don't be null");
        }

        if (presenter != null)
            presenter.createPresenter((IBaseView) this);

        this.initView(savedInstanceState);

        if (dispatch == null) {
            dispatch = attachEvent(new EventDispatch(), this);
        }

    }

    protected void setStatusBar() {
//        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorStatusBar), 0);
//        StatusBarUtil.setLightMode(this);
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
    protected void onResume() {
        super.onResume();
        if (isCreated) {
            this.resumeView();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.destroyView();
        if (presenter != null)
            presenter.destroyPresenter();
        unbinder.unbind();
        detachEvent();
    }

    protected void gotoActivity(Class cl, @Nullable Object object) {
        gotoActivity(cl, object, true);
    }

    protected void gotoActivity(Class cl, @Nullable Object object, boolean isSticky) {
        Intent intent = new Intent(this, cl);
        if (object != null && dispatch != null) {
            dispatch.post(object, isSticky);
        }
        startActivity(intent);
//        overridePendingTransition(R.anim.slide_right_in, R.anim.still);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        overridePendingTransition(R.anim.still, R.anim.slide_left_out);
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

}
