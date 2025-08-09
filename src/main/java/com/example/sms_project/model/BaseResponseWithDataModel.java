package com.example.sms_project.model;

import lombok.Getter;

@Getter
public class BaseResponseWithDataModel extends BaseResponseModel {
    private Object products;

    public BaseResponseWithDataModel(String status, String message, Object products) {
        super(status, message);
        this.products = products;
    }
}
