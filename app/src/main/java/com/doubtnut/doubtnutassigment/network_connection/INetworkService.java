package com.doubtnut.doubtnutassigment.network_connection;



import com.doubtnut.doubtnutassigment.model.CountryData;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Rahul on 2/09/17.
 */

public interface INetworkService {

    @GET("rest/v2/all")
    Observable<List<CountryData>> getCountryDataList();
}
