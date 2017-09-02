package com.doubtnut.doubtnutassigment.presenter;


import com.doubtnut.doubtnutassigment.model.CountryData;
import com.doubtnut.doubtnutassigment.network_connection.CommonErrors;
import com.doubtnut.doubtnutassigment.network_connection.NetworkService;
import com.doubtnut.doubtnutassigment.view.IHomeView;

import java.util.List;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Rahul on 2/09/17.
 */

public class HomePresenter {
    private final NetworkService service;
    private final IHomeView view;
    private CompositeSubscription subscriptions;

    public HomePresenter(NetworkService service, IHomeView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getCountryDataList() {
        view.showProgressBar();

        Subscription subscription = service.getCountryDataList(new NetworkService.GetCountryDataListCallback() {
            @Override
            public void onSuccess(List<CountryData> response) {
                view.removeProgressBar();
                view.getCountryDataList(response);
            }

            @Override
            public void onError(CommonErrors networkError) {
                view.removeProgressBar();
                view.onFailure(networkError.getAppErrorMessage());
            }

        });

        subscriptions.add(subscription);
    }
    public void onStop() {
        subscriptions.unsubscribe();
    }
}
