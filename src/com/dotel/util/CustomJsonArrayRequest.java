package com.dotel.util;
/*

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

public class CustomJsonArrayRequest extends Request<JSONArray> {
	
	private Listener<JSONArray> response;
	private Map<String,String> params;

	public CustomJsonArrayRequest(int method, String url, Map<String,String> params,
			Listener<JSONArray> response, ErrorListener errorListener) {
		
		super(method, url, errorListener);
		this.params = params;
		this.response = response;
		
	}
	
	public CustomJsonArrayRequest(String url, Map<String,String> params,
			Listener<JSONArray> response, ErrorListener errorListener) {
		
		super(Method.GET, url, errorListener);
		this.params = params;
		this.response = response;
		
	}
	
	public Map<String,String> getParams() throws AuthFailureError{
		return params;
	}

	@Override
	protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
		
		try {
			String js = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
			return(Response.success(new JSONArray(js), HttpHeaderParser.parseCacheHeaders(response)));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void deliverResponse(JSONArray response) {
		
		this.response.onResponse(response);
	}

}
*/