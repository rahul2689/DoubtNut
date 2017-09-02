package com.doubtnut.doubtnutassigment.network_connection;



import com.doubtnut.doubtnutassigment.model.CountryData;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Rahul on 2/09/17.
 */

public class NetworkService {

    private final INetworkService networkService;

    public NetworkService(INetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getCountryDataList(final GetCountryDataListCallback callback) {

        return networkService.getCountryDataList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends List<CountryData>>>() {
                    @Override
                    public Observable<? extends List<CountryData>> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<List<CountryData>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new CommonErrors(e));

                    }

                    @Override
                    public void onNext(List<CountryData> response) {
                        callback.onSuccess(response);

                    }
                });
    }

    public interface GetCountryDataListCallback {
        void onSuccess(List<CountryData> response);

        void onError(CommonErrors errors);
    }
}
