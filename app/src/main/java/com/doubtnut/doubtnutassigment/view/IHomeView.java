package com.doubtnut.doubtnutassigment.view;


import com.doubtnut.doubtnutassigment.model.CountryData;

import java.util.List;

/**
 * Created by root on 21/6/17.
 */

public interface IHomeView {
    void showProgressBar();

    void removeProgressBar();

    void onFailure(String appErrorMessage);

    void getCountryDataList(List<CountryData> usersResponse);
}
