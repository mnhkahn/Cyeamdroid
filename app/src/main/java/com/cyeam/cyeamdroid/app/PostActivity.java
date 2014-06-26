package com.cyeam.cyeamdroid.app;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.cyeam.cyeamdroid.model.Blog;

/**
 * Created by bryce on 14-6-25.
 */
public class PostActivity extends Activity {
    private Blog blog;
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Blog blog = (Blog)getIntent().getSerializableExtra(Blog.BLOG_SER);
        this.blog = blog;
        System.out.println(blog.getPubDate());
        webView = (WebView)findViewById(R.id.post);
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.loadDataWithBaseURL("", blog.getDescription(), "text/html", "UTF-8","");
    }
}
