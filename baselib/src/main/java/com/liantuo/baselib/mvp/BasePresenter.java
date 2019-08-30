package com.liantuo.baselib.mvp;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.RxFragment;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dell on 2018/4/24.
 */

public class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {

    protected V view;
    private CompositeDisposable compositeDisposable;

    @Override
    public void createPresenter(V view) {
        this.view = view;
    }

    public void destroyPresenter() {
        if (compositeDisposable != null)
            compositeDisposable.clear();

        this.compositeDisposable = null;
        this.view = null;
    }

    public void addSubscribe(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    /**
     * 统一线程调度处理
     *
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<T, T> rxSchedulingThread() {
        return rxSchedulingThread(0);
    }

    public static <T> ObservableTransformer<T, T> rxSchedulingThread(int timeout) {
        return observable -> observable.delay(timeout, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 统一返回结果处理
     *
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<T, T> rxHandleResult() {
        return httpResponseObservable ->
                httpResponseObservable.flatMap((Function<T, Observable<T>>) response -> {
                    if (response != null) {
                        return dispatch(response);
                    } else {
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    /**
     * 得到 Observable
     *
     * @param <T> 指定的泛型类型
     * @return Observable
     */
    private static <T> Observable<T> dispatch(T t) {
        return Observable.create(emitter -> {
            try {
                emitter.onNext(t);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }


    /**
     * 绑定 Activity/Fragment 的生命周期
     *
     * @param
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T> LifecycleTransformer<T> bindToLifecycle(@NonNull LifecycleProvider lifecycleProvider) {
        if (lifecycleProvider instanceof RxAppCompatActivity) {
            return lifecycleProvider.bindToLifecycle();
        } else if (lifecycleProvider instanceof RxFragment) {
            return lifecycleProvider.bindToLifecycle();
        } else {
            throw new IllegalArgumentException("LifecycleAble not match");
        }
    }

}
