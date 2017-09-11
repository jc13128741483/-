package com.hello.web.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyRequest extends  HttpServletRequestWrapper{
	
	private Map<String,String[]> params =new HashMap<String, String[]>();
	
	public MyRequest(HttpServletRequest request) {
		super(request);
	//-----------------------------------------------
		
		Map<String,String[]> map = super.getParameterMap();
		
		if(map!=null && map.size()>0){
			for(Entry<String, String[]> en : map.entrySet()){
				
				String[] values = en.getValue();
				if(values!=null){
				String[] newValues = new String[values.length];
					for(int i = 0; i < values.length ;i++){
						try {
							newValues[i] = new String(values[i].getBytes("ISO-8859-1"),"UTF-8");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}
					
					params.put(en.getKey(), newValues);
					}
			}
		}
		
	}

	@Override
	public String getParameter(String name) {
		String[] values = params.get(name);
		
		if(values==null){
			return null;
		}
		
		return values[0];
	}

	@Override
	public Map<String,String[]> getParameterMap() {
		
		return params;
	}
	
	@Override
	public String[] getParameterValues(String name) {
		return params.get(name);
	}

	

}
