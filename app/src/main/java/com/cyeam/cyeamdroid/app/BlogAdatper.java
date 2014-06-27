package com.cyeam.cyeamdroid.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cyeam.cyeamdroid.model.Blog;

import java.util.List;

/**
 * Created by bryce on 14-6-25.
 */
public class BlogAdatper extends ArrayAdapter<Blog> {
    private int resource;

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

//        final ImageView figureImageView = (ImageView)blogListView.findViewById(R.id.blog_figure);
//        if (blog.getFigure() == null || blog.getFigure().equals("")) {
//            figureImageView.setVisibility(View.INVISIBLE);
//        } else {
//            CyeamHttp.get(blog.getFigure(), null, new TextHttpResponseHandler() {
//                @Override
//                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                    super.onSuccess(statusCode, headers, responseBody);
//                    figureImageView.setImageBitmap(BitmapFactory.decodeByteArray(responseBody, 0, responseBody.length));
//                }
//
//                @Override
//                public void onFailure(String responseBody, Throwable error) {
//                    super.onFailure(responseBody, error);
//                }
//            });
//        }

        return blogListView;
    }

}
