package com.project.earthquake.ui;

import com.project.earthquake.data.network.EarthQuakeItem;
import com.project.earthquake.data.network.EarthQuakeRsp;
import com.project.earthquake.repository.IEarthQuakeRepository;
import com.project.earthquake.ui.common.EarthQuakeBaseViewModel;
import com.project.earthquake.util.Resource;

import org.reactivestreams.Publisher;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class EarthQuakeListViewModel extends EarthQuakeBaseViewModel {

    protected IEarthQuakeRepository earthQuakeRepository;

    @Inject
    public EarthQuakeListViewModel(IEarthQuakeRepository repository) {
        earthQuakeRepository = repository;
    }

    public LiveData<Resource<EarthQuakeRsp>> getEarthQuakeData(float north,
                                                                     float south,
                                                                     float east,
                                                                     float west,
                                                                     String userName) {
        Flowable<EarthQuakeRsp> earthQuakeItemFlowable = earthQuakeRepository
                .getEarthQuakesData(north, south, east, west, userName);

        LiveData<Resource<EarthQuakeRsp>> lv= getLiveDataFrom(earthQuakeItemFlowable);
        return lv;

    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        private final IEarthQuakeRepository repository;

        public Factory(IEarthQuakeRepository repository) {
            this.repository = repository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if(modelClass == EarthQuakeListViewModel.class) {
                return (T) new EarthQuakeListViewModel(repository);
            } else {
                return super.create(modelClass);
            }
        }
    }
}


