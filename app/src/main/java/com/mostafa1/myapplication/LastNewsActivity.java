package com.mostafa1.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;


public class LastNewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_last_news );
        ApiService apiService=new ApiService(this);

        apiService.getPosts( new ApiService.OnPostsReceived() {
            @Override
            public void onReceived(List <Post> posts) {
                SevenLearnDatabaseOpenHelper openHelper=new SevenLearnDatabaseOpenHelper(LastNewsActivity.this);
                openHelper.addPosts(posts);
                RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
                Log.i( "caosncoasncoasncoas",""+posts.size() );
                NewsAdapter newsAdapter=new NewsAdapter(LastNewsActivity.this,posts);
                recyclerView.setLayoutManager(new LinearLayoutManager(LastNewsActivity.this,LinearLayoutManager.VERTICAL,false));
                recyclerView.setAdapter(newsAdapter);

            }
        } );




    }
}
