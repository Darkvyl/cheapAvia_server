package com.darkvyl.cheapavia.service;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

import javax.validation.constraints.NotNull;
import java.io.IOException;

class HeaderRequestInterceptor implements ClientHttpRequestInterceptor {

    private final String headerName;
    private final String headerValue;

    HeaderRequestInterceptor(String headerName, String headerValue) {
        this.headerName = headerName;
        this.headerValue = headerValue;
    }

    @Override
    @NotNull
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution)
            throws IOException {
        HttpRequest wrapper = new HttpRequestWrapper(request);
        wrapper.getHeaders().set(headerName, headerValue);
        return execution.execute(wrapper, body);
    }
}
