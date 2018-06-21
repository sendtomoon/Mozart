package com.sendtomoon.mozart.service;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.methods.Configurable;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;

public abstract class HttpClientSB extends HttpRequestBase implements HttpEntityEnclosingRequest,HttpUriRequest, Configurable {

}
