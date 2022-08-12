package com.duongnv.springvuejsbackend.security;

import org.apache.http.client.methods.HttpRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;

public class HttpWrapper extends HttpServletRequestWrapper {

    private HashMap<String, String> params = new HashMap<>();

    public HttpWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String param) {
        if(params.containsKey(param)) {
            return params.get(param);
        } else {
            return super.getParameter(param);
        }
    }

    public void addParameter(String key, String value) {
        params.put(key, value);
    }

}
