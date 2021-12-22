package com.justino.sistempakar.berita;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetResult {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Result> listDataResult;
    @SerializedName("message")
    String message;

    public GetResult(String status, List<Result> listDataResult, String message) {
        this.status = status;
        this.listDataResult = listDataResult;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Result> getListDataResult() {
        return listDataResult;
    }

    public void setListDataResult(List<Result> listDataResult) {
        this.listDataResult = listDataResult;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
