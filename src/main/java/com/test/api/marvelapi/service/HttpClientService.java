package com.test.api.marvelapi.service;

import java.util.Map;

public interface HttpClientService {

    <T> T doGet(String endpoint, Map<String, String> qeuryParams, Class<T> responseType);
    <T, R> T doPost(String endpoint, Map<String, String> qeuryParams, Class<T> responseType, R bodyRequest);
    <T, R> T doPut(String endpoint, Map<String, String> qeuryParams, Class<T> responseType, R bodyRequest);
    <T> T doDelete(String endpoint, Map<String, String> qeuryParams, Class<T> responseType);
}
