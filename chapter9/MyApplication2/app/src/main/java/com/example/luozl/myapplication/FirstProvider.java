package com.example.luozl.myapplication;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by luozl on 2017/12/15.
 */

public class FirstProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        System.out.println("--onCreate 方法被调用 --");
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
       System.out.println(uri+"==query方法被调用==");
        System.out.println("where 参数为："+selection);
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        System.out.println(uri+"==insert==");
        System.out.println("values 参数为："+values);
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        System.out.println(uri+"==delete==");
        System.out.println("where 参数为："+selection);
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        System.out.println(uri+"==delete方法被调用==");
        System.out.println("where 参数为："+selection+",values 参数为："+values);
        return 0;
    }
}
