package com.cyeam.cyeamdroid.app;

import android.content.Context;
import android.graphics.Bitmap;

import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cyeam.cyeamdroid.http.AsyncImageLoader;
import com.cyeam.cyeamdroid.model.Blog;

import java.util.List;

/**
 * Created by bryce on 14-6-25.
 */
public class BlogAdatper extends ArrayAdapter<Blog> {
    private int resource;

    private final int maxMemory = (int) Runtime.getRuntime().maxMemory();//获取当前应用程序所分配的最大内存
    private final int cacheSize = maxMemory / 5;//只分5分之一用来做图片缓存
    private LruCache<String, Bitmap> mLruCache = new LruCache<String, Bitmap>(
            cacheSize) {
        @Override
        protected int sizeOf(String key, Bitmap bitmap) {//复写sizeof()方法
            // replaced by getByteCount() in API 12
            return bitmap.getRowBytes() * bitmap.getHeight() / 1024; //这里是按多少KB来算
        }
    };

    public BlogAdatper(Context context, int resource, List<Blog> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout blogListView = null;

        Blog blog = getItem(position);
        String title = blog.getTitle();

        if (convertView == null) {
            blogListView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
                    inflater);
            vi.inflate(this.resource, blogListView, true);
        } else {
            blogListView = (LinearLayout) convertView;
        }

        TextView titleTextView = (TextView) blogListView
                .findViewById(R.id.blog_title);
        titleTextView.setText(title);

        TextView descTextView = (TextView) blogListView.findViewById(R.id.blog_description);
        descTextView.setText(blog.getInfo());

        TextView dateITextView = (TextView) blogListView.findViewById(R.id.blog_date);
        dateITextView.setText(Formatter.formatter.format(blog.getPubDate()));

        ImageView figureImageView = (ImageView)blogListView.findViewById(R.id.blog_figure);
        if (blog.getFigure() != null && !blog.getFigure().equals("")) {
            loadBitmap(blog.getFigure(), figureImageView);
        }

        return blogListView;
    }

    private void loadBitmap(String urlStr, ImageView image) {
        AsyncImageLoader asyncLoader = new AsyncImageLoader(image, mLruCache);//什么一个异步图片加载对象
        Bitmap bitmap = asyncLoader.getBitmapFromMemoryCache(urlStr);//首先从内存缓存中获取图片
        if (bitmap != null) {
            image.setImageBitmap(bitmap);//如果缓存中存在这张图片则直接设置给ImageView
        } else {
            image.setImageResource(R.drawable.ic_launcher);//否则先设置成默认的图片
            asyncLoader.execute(urlStr);//然后执行异步任务AsycnTask 去网上加载图片
        }
    }

}
