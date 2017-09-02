package com.doubtnut.doubtnutassigment.network_connection;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rahul on 2/09/17.
 */

public class CommonResponse {

    @SerializedName("status")
    public String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @SuppressWarnings({"unused", "used by Retrofit"})
    public CommonResponse() {
    }

    public CommonResponse(String status) {
        this.status = status;
    }

}


