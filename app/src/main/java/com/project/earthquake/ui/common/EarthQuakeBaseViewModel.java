package com.project.earthquake.ui.common;
import com.project.earthquake.util.Resource;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Base class for all view models
 */
public class EarthQuakeBaseViewModel extends ViewModel {
    private final CompositeDisposable disposables = new CompositeDisposable();

    protected final <T> LiveData<Resource<T>> getLiveDataFrom(Flowable<T> upstream) {
        MutableLiveData<Resource<T>> newlv = new MutableLiveData<>();
        bindLiveDataFrom(upstream, newlv);
        return newlv;
    }

    protected final <T> void bindLiveDataFrom(Flowable<T> upstream,
                                              MutableLiveData<Resource<T>> data) {
        disposables.add(upstream
                .map(Resource::success)
                .startWith(Resource.loading())
                .onErrorReturn(Resource::error)
                .subscribe(data::postValue));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
