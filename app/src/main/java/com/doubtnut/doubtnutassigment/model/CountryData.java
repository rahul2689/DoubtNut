package com.doubtnut.doubtnutassigment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.Generated;

/**
 * Created by Rahul on 2/09/17.
 */
@Generated("org.jsonschema2pojo")
public class CountryData implements Serializable {

    @SerializedName("name")
    @Expose
    private String countryName;

    @SerializedName("capital")
    @Expose
    private String countryCapital;


    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCapital() {
        return countryCapital;
    }

    public void setCountryCapital(String countryCapital) {
        this.countryCapital = countryCapital;
    }

}
