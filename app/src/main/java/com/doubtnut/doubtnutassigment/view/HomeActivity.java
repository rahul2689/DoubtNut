package com.doubtnut.doubtnutassigment.view;

/**
 * Created by Rahul on 2/09/17.
 */

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.doubtnut.doubtnutassigment.BaseActivity;
import com.doubtnut.doubtnutassigment.R;
import com.doubtnut.doubtnutassigment.model.CountryData;
import com.doubtnut.doubtnutassigment.network_connection.NetworkService;
import com.doubtnut.doubtnutassigment.presenter.HomePresenter;
import com.doubtnut.doubtnutassigment.storage.ReadWriteDataFromLocal;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class HomeActivity extends BaseActivity implements IHomeView {

    private RecyclerView mListRv;
    @Inject
    public NetworkService mNetworkService;
    private ProgressBar mProgressBar;
    private HomeAdapter mAdapter;
    private HomePresenter mPresenter;
    private List<CountryData> mCountryDataList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDaggerInjector().inject(this);
        renderView();
        setUpData();
        init();
    }

    private void setUpData() {
        if(ReadWriteDataFromLocal.getCountrysData(this) != null){
            mCountryDataList = ReadWriteDataFromLocal.getCountrysData(this);
        }else {
            mPresenter = new HomePresenter(mNetworkService, this);
            mPresenter.getCountryDataList();
        }
    }

    public void renderView() {
        setContentView(R.layout.activity_home);
        setUpToolbar();
        mListRv = (RecyclerView) findViewById(R.id.rv_country_list);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
    }

    public void init() {
        mListRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new HomeAdapter(this, mCountryDataList,
                new HomeAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(CountryData countryData) {
                       String toastMsg =  getResources().getString(R.string.toast_msg_country) + " " +
                               countryData.getCountryName() + " " +
                               getResources().getString(R.string.toast_msg_capital) + " " +countryData.getCountryCapital();
                       Toast.makeText(HomeActivity.this, toastMsg, Toast.LENGTH_LONG).show();
                    }
                });

        mListRv.setAdapter(mAdapter);
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {
        Toast.makeText(getApplicationContext(), appErrorMessage,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void getCountryDataList(List<CountryData> countryResponse) {
         if(mAdapter != null){
             ReadWriteDataFromLocal.writeCountrysData(countryResponse, HomeActivity.this);
             mAdapter.updateCountryDataList(countryResponse);
         }
    }
}