package com.cyeam.cyeamdroid.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cyeam.cyeamdroid.http.CyeamHttp;
import com.cyeam.cyeamdroid.model.Blog;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import java.util.List;

/**
 * Created by bryce on 14-6-25.
 */
public class PostActivity extends Activity {
    private Blog blog;
    private TextView title;
    private TextView date;
    private ImageView figure;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*显示App icon左侧的back键*/
        getActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Blog blog = (Blog) getIntent().getSerializableExtra(Blog.BLOG_SER);
        this.blog = blog;

        getActionBar().setTitle(blog.getTitle());

        title = (TextView) findViewById(R.id.post_title);
        date = (TextView) findViewById(R.id.post_date);
        figure = (ImageView) findViewById(R.id.post_figure);
        webView = (WebView) findViewById(R.id.post);
    }

    @Override
    protected void onResume() {
        super.onResume();
        title.setText(blog.getTitle());
        date.setText(Formatter.formatter.format(blog.getPubDate()));

        if (blog.getFigure() != null && !blog.getFigure().equals("")) {
            CyeamHttp.get(blog.getFigure(), null, new TextHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    super.onSuccess(statusCode, headers, responseBody);
                    figure.setImageBitmap(BitmapFactory.decodeByteArray(responseBody, 0, responseBody.length));
                }

                @Override
                public void onFailure(String responseBody, Throwable error) {
                    super.onFailure(responseBody, error);
                }
            });
        }

        webView.loadDataWithBaseURL("", blog.getDescription(), "text/html", "UTF-8", "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
            case R.id.action_share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TITLE, blog.getTitle());
                intent.putExtra(Intent.EXTRA_SUBJECT, blog.getLink());
                intent.putExtra(Intent.EXTRA_TEXT, blog.getInfo() + " " + blog.getLink());

                Intent chooserIntent = Intent.createChooser(intent, "Select app to share");
                try {
                    startActivity(chooserIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(this, "Can't find share component to share", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
}
