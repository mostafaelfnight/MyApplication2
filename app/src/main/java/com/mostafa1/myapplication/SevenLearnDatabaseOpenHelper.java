package com.mostafa1.myapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SevenLearnDatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseOpenHelper";

    private static final String DATABASE_NAME="db_7learn";
    private static final int DATABASE_VERSION=1;

    private static final String POST_TABLE_NAME="tbl_posts";


    private static final String COL_ID="col_id";
    private static final String COL_TITLE="col_title";
    private static final String COL_CONTENT="col_content";
    private static final String COL_POST_IMAGE_URL="col_post_image_url";
    private static final String COL_IS_VISITED="col_is_visited";
    private static final String COL_DATE="col_date";

    private static final String SQL_COMMAND_CREATE_POST_TABLE="CREATE TABLE IF NOT EXISTS "+POST_TABLE_NAME+"("+
            COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            COL_TITLE+" TEXT,"+
            COL_CONTENT+" TEXT, "+
            COL_POST_IMAGE_URL+" TEXT, "+
            COL_IS_VISITED+" INTEGER DEFAULT 0, "+
            COL_DATE+" TEXT);";

    Context context;
    public SevenLearnDatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(SQL_COMMAND_CREATE_POST_TABLE);
        }catch (SQLException e){
            Log.e(TAG, "onCreate: "+e.toString() );
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public boolean addPost(Post post){
        ContentValues cv=new ContentValues();
        cv.put(COL_TITLE,post.getTitle());
        cv.put(COL_CONTENT,post.getContent());
        cv.put(COL_POST_IMAGE_URL,post.getPostImageUrl());
        cv.put(COL_IS_VISITED,post.getIsVisited());
        cv.put(COL_DATE,post.getDate());

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        long isInserted=sqLiteDatabase.insert(POST_TABLE_NAME,null,cv);

        Log.i(TAG, "addPost: "+isInserted);

        if (isInserted>0){
            return true;
        }else{
            return false;
        }
    }

    public void addPosts(List<Post> posts){
        for (int i = 0; i < posts.size(); i++) {
            addPost(posts.get(i));
        }
    }

    public List<Post> getPosts(){
        return new ArrayList<>();
    }

    public void setPostIsVisited(int isVisited){

    }

}
