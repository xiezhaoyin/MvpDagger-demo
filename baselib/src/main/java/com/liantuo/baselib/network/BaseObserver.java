package com.liantuo.baselib.network;

import android.util.Log;

import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.ListCompositeDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.EndConsumerHelper;

/**
 * Created by dell on 2018/4/25.
 */

public class BaseObserver<T> implements Observer<T>, Disposable {

    public OnCallback<T> listener = null;

    /**
     * The active subscription.
     */
    private final AtomicReference<Disposable> upstream = new AtomicReference<Disposable>();
    /**
     * The resource composite, can never be null.
     */
    private final ListCompositeDisposable resources = new ListCompositeDisposable();

    public BaseObserver(OnCallback<T> listener) {
        this.listener = listener;
    }

    /**
     * Adds a resource to this ResourceObserver.
     *
     * @param resource the resource to add
     * @throws NullPointerException if resource is null
     */
    public final void add(@NonNull Disposable resource) {
        ObjectHelper.requireNonNull(resource, "resource is null");
        resources.add(resource);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if (EndConsumerHelper.setOnce(this.upstream, d, getClass())) {
            onStart();
        }
    }

    protected void onStart() {

    }

    @Override
    public void onNext(@NonNull T t) {
        if (listener != null)
            listener.onCall(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        Log.d("BaseObserver", "Exception == " + e.getMessage());
        if (listener != null) {
            listener.onError("-100", e.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void dispose() {
        if (DisposableHelper.dispose(upstream)) {
            resources.dispose();
        }
    }

    @Override
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(upstream.get());
    }
}