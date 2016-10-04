package com.lidong.demo.view.provider;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lidong.demo.mvp.api.ApiManager;
import com.lidong.demo.mvp.bean.DreamData;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by lidong on 16/7/17.
 */
public class DreamSuggestionProvider extends ContentProvider {

    private static final String AUTHORITY = "com.lidong.demo.dreamsuggestion";

    private static final int TYPE_ALL_SUGGESTIONS = 1;
    private static final int TYPE_SINGLE_SUGGESTION = 2;

    private UriMatcher mUriMatcher;

    private static final String TAG = DreamSuggestionProvider.class.getSimpleName() ;
    private List<DreamData.ResultBean> results;

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate: ------------");
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(AUTHORITY, "/#", TYPE_SINGLE_SUGGESTION);
        mUriMatcher.addURI(AUTHORITY, "suggestions/search_suggest_query/*", TYPE_ALL_SUGGESTIONS);
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        Log.d(TAG, "query: "+uri.getAuthority());
        if (results == null || results.isEmpty()){

            ApiManager.getDreamData("c73b082b0c150b3bcba2cea1b96a8922").subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<DreamData>() {
                        @Override
                        public void call(DreamData dreamData) {
                            if (dreamData!=null && "successed".equals(dreamData.getReason()))
                                results =  dreamData.getResult();
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                           Log.d(TAG, "call: " + throwable.getMessage());
                        }
                    });
        }


        MatrixCursor cursor = new MatrixCursor(
                new String[] {
                        BaseColumns._ID,
                        SearchManager.SUGGEST_COLUMN_TEXT_1,
                        SearchManager.SUGGEST_COLUMN_INTENT_DATA_ID
                }
        );
        if (results != null) {
            String query = uri.getLastPathSegment().toUpperCase();
            int limit = Integer.parseInt(uri.getQueryParameter(SearchManager.SUGGEST_PARAMETER_LIMIT));

            int lenght = results.size();
            for (int i = 0; i < lenght && cursor.getCount() < limit; i++) {
                DreamData.ResultBean city = results.get(i);
                if (city.getName().toUpperCase().contains(query)){
                    cursor.addRow(new Object[]{ i, city, i });
                }
            }
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
