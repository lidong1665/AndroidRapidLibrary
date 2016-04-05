package com.lidong.demo.AndFix.bean.util;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalPreferencesHelper {
	private SharedPreferences mSharedPreferences;

	public LocalPreferencesHelper(Context context, String dbName) {
		mSharedPreferences = context.getSharedPreferences(dbName,
				Context.MODE_PRIVATE);
	}

	public void saveOrUpdate(String key, String value) {
		mSharedPreferences.edit().putString(key, value).commit();
	}

	public void saveOrUpdate(String key, int value) {
		mSharedPreferences.edit().putInt(key, value).commit();
	}

	public void saveOrUpdate(String key, float value) {
		mSharedPreferences.edit().putFloat(key, value).commit();
	}

	public void saveOrUpdate(String key, boolean value) {
		mSharedPreferences.edit().putBoolean(key, value).commit();
	}

	public void saveOrUpdate(String key, long value) {
		mSharedPreferences.edit().putLong(key, value).commit();
	}

	public void del(String key) {
		mSharedPreferences.edit().remove(key).commit();
	}

	public float getFloat(String key) {
		return mSharedPreferences.getFloat(key, -1);
	}

	public String getString(String key) {
		return mSharedPreferences.getString(key, "");
	}

	public boolean getBooleanDefaultFalse(String key) {
		return mSharedPreferences.getBoolean(key, false);
	}

    public boolean getBooleanDefaultTrue(String key) {
        return mSharedPreferences.getBoolean(key, true);
    }

	public int getInt(String key) {
		return mSharedPreferences.getInt(key, -1);
	}

	public int getInt(String key, int defValue) {
		return mSharedPreferences.getInt(key, defValue);
	}

	public long getLong(String key) {
		return mSharedPreferences.getLong(key, -1);
	}

	public long getLong(String key, long defValue) {
		return mSharedPreferences.getLong(key, defValue);
	}
}