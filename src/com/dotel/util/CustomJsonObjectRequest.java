package com.dotel.util;

/*
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

public class CustomJsonObjectRequest extends Request<JSONObject> {
	
	private Listener<JSONObject> response;
	private Map<String,String> params;

	public CustomJsonObjectRequest(int method, String url, Map<String,String> params,
			Listener<JSONObject> response, ErrorListener errorListener) {
		
		super(method, url, errorListener);
		this.params = params;
		this.response = response;
		
	}
	
	public CustomJsonObjectRequest(String url, Map<String,String> params,
			Listener<JSONObject> response, ErrorListener errorListener) {
		
		super(Method.GET, url, errorListener);
		this.params = params;
		this.response = response;
		
	}
	
	public Map<String,String> getParams() throws AuthFailureError{
		return params;
	}

	@Override
	protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
		
		try {
			String js = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
			return(Response.success(new JSONObject(js.toString()), HttpHeaderParser.parseCacheHeaders(response)));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected String getStringResponse(NetworkResponse response){
		try {
			return new String(response.data, HttpHeaderParser.parseCharset(response.headers));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	protected void deliverResponse(JSONObject response) {
		
		this.response.onResponse(response);
	}

}
*/