package com.lidong.demo.AndFix.bean.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;

/**
*@类名 : GsonUtils
*@描述 : 
*@时间 : 2016/4/12  14:25
*@作者: 李东
*@邮箱  : lidong@chni.com.cn
*@company: chni
*/
public class GsonUtils {

	private Gson mGson;

	private GsonUtils() {
		mGson = new GsonBuilder()
				.serializeNulls().setPrettyPrinting() // 对json结果格式化.
				.create();
	}

	private static class SingletonHolder {
		public static final GsonUtils INSTANCE = new GsonUtils();
	}

	public static GsonUtils getInstance() {
		return SingletonHolder.INSTANCE;
	}

	public Gson getGson() {
		return mGson;
	}

	public <T> T parseIfNull(Type typeOfT, InputStream is) {
		try {
			return mGson.fromJson(new InputStreamReader(is), typeOfT);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

	public <T> T parseIfNull(Type typeOfT, String json) {
		try {
			return mGson.fromJson(json, typeOfT);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

	public <T> T parse(Type typeOfT, String json) throws Exception {
		try {
			return mGson.fromJson(json, typeOfT);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	public <T> T parseIfNull(Type typeOfT, Reader json) {
		try {
			return mGson.fromJson(json, typeOfT);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

	public <T> T parse(Type typeOfT, Reader json) throws Exception {
		try {
			return mGson.fromJson(json, typeOfT);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	public <T> T parseIfNull(Class<T> clazz, String json) {
		try {
			return mGson.fromJson(json, clazz);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

	public <T> T parse(Class<T> clazz, String json) throws Exception {
		try {
			return mGson.fromJson(json, clazz);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	public <T> String parseIfNull(T object) {
		try {
			return mGson.toJson(object);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

	public <T> String parse(T object) throws Exception {
		try {
			return mGson.toJson(object);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}
}
